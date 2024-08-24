package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.repository.ICandidateRepository;
import com.wendersonp.voting.domain.repository.IPositionRepository;
import com.wendersonp.voting.domain.service.ISectionValidationService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class SectionValidationServiceImpl implements ISectionValidationService {

    private final ICandidateRepository candidateRepository;

    private final IPositionRepository positionRepository;


    public SectionValidationServiceImpl(
            ICandidateRepository candidateRepository,
            IPositionRepository positionRepository) {
        this.candidateRepository = candidateRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public boolean validateToOpenSection(Set<UUID> candidateIds, UUID positionId) {
        return areCandidateIdsValid(candidateIds) && isPositionIdValid(positionId);
    }

    @Override
    public boolean validateToCloseSection(SectionEntity section) {
        return section.isOpen();
    }

    @Override
    public boolean validateAppurateSection(SectionEntity section) {
        return section.isClosed();
    }

    private boolean areCandidateIdsValid(Set<UUID> candidateIds) {
        int candidatesCount = candidateRepository.countCandidatesById(candidateIds);
        return candidatesCount >= candidateIds.size();
    }

    private boolean isPositionIdValid(UUID positionId) {
        return positionRepository.existsById(positionId);
    }


}
