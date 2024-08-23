package com.wendersonp.voting.application.service.impl;

import com.wendersonp.voting.application.dto.OpenSectionDTO;
import com.wendersonp.voting.application.exception.BadRequestException;
import com.wendersonp.voting.application.exception.NotFoundException;
import com.wendersonp.voting.application.service.ISectionService;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.SectionReportEntity;
import com.wendersonp.voting.domain.repository.ISectionRepository;
import com.wendersonp.voting.domain.service.ISectionAppurationService;
import com.wendersonp.voting.domain.service.ISectionBulletinBuilderService;
import com.wendersonp.voting.domain.service.ISectionValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SectionServiceImpl implements ISectionService {
    private final ISectionRepository sectionRepository;

    private final ISectionValidationService sectionValidationService;

    private final ISectionAppurationService sectionAppurationService;

    private final ISectionBulletinBuilderService sectionBulletinService;

    public SectionServiceImpl(
            ISectionRepository sectionRepository,
            ISectionValidationService sectionValidationService, ISectionAppurationService sectionAppurationService, ISectionBulletinBuilderService sectionBulletinService) {
        this.sectionRepository = sectionRepository;
        this.sectionValidationService = sectionValidationService;
        this.sectionAppurationService = sectionAppurationService;
        this.sectionBulletinService = sectionBulletinService;
    }


    @Override
    public void openSection(OpenSectionDTO sectionDTO) {
        boolean validationResult = sectionValidationService
                .validateOpenSection(sectionDTO.candidatesRunningIds(), sectionDTO.runningPosition());
        if (!validationResult) {
            throw new BadRequestException("Sessão não pode ser aberta");
        }

        SectionEntity sectionEntity = sectionDTO.toEntity();
        sectionEntity.openSection();
        sectionRepository.save(sectionEntity);
    }

    @Override
    @Transactional
    public void closeSection(UUID sectionId) {
        SectionEntity sectionEntity = findSection(sectionId);
        boolean canCloseSection = sectionValidationService.validateCloseSection(sectionEntity);
        if (!canCloseSection) {
            throw new BadRequestException("Sessão nao pode ser fechada");
        }
        sectionEntity.closeSection();
        sectionRepository.save(sectionEntity);
    }

    @Override
    @Transactional
    public String generateBulletin(UUID sectionId) {
        SectionEntity sectionEntity = findSection(sectionId);
        boolean isClosedSection = sectionValidationService.validateAppurateSection(sectionEntity);

        if (!isClosedSection) {
            throw new BadRequestException("Apenas seção fechada pode ser apurada");
        }

        SectionReportEntity reportEntity = sectionAppurationService.countVotes(sectionEntity);
        return sectionBulletinService.buildBulletin(reportEntity);
    }

    private SectionEntity findSection(UUID sectionId) {
        return sectionRepository
                .findById(sectionId)
                .orElseThrow(NotFoundException::new);
    }
}
