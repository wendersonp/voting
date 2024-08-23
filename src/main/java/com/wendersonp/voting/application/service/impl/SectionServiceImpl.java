package com.wendersonp.voting.application.service.impl;

import com.wendersonp.voting.application.dto.OpenSectionDTO;
import com.wendersonp.voting.application.exception.BadRequestException;
import com.wendersonp.voting.application.exception.NotFoundException;
import com.wendersonp.voting.application.service.ISectionService;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.repository.ISectionRepository;
import com.wendersonp.voting.domain.service.ISectionValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SectionServiceImpl implements ISectionService {
    private final ISectionRepository sectionRepository;

    private final ISectionValidationService sectionValidationService;

    public SectionServiceImpl(
            ISectionRepository sectionRepository,
            ISectionValidationService sectionValidationService) {
        this.sectionRepository = sectionRepository;
        this.sectionValidationService = sectionValidationService;
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
        SectionEntity sectionEntity = sectionRepository
                .findById(sectionId)
                .orElseThrow(NotFoundException::new);
        boolean canCloseSection = sectionValidationService.validateCloseSection(sectionEntity);
        if (!canCloseSection) {
            throw new BadRequestException("Sessão nao pode ser fechada");
        }
        sectionEntity.closeSection();
        sectionRepository.save(sectionEntity);
    }
}
