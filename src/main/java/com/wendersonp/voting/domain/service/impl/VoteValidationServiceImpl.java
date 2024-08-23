package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.application.exception.NotFoundException;
import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.domain.repository.ISectionRepository;
import com.wendersonp.voting.domain.repository.IVoteRepository;
import com.wendersonp.voting.domain.service.IVoteValidationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VoteValidationServiceImpl implements IVoteValidationService {

    private final IVoteRepository voteRepository;

    private final ISectionRepository sectionRepository;

    public VoteValidationServiceImpl(IVoteRepository voteRepository, ISectionRepository sectionRepository) {
        this.voteRepository = voteRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public boolean isVoteValid(VoteEntity vote) {
        SectionEntity section = findSection(vote.getSection().getId())
                .orElseThrow(NotFoundException::new);
        return doesVoteNotExist(vote)
                && isSectionOpen(section)
                && isCandidateRunning(vote.getCandidate().getId(), section);
    }

    private boolean doesVoteNotExist(VoteEntity vote) {
        return !voteRepository.existsBySectionIdAndCandidateIdAndVoterId(
                vote.getSection().getId(), vote.getCandidate().getId(), vote.getVoter().getId()
        );
    }

    private boolean isSectionOpen(SectionEntity sectionEntity) {
        return sectionEntity.isOpen();
    }

    private boolean isCandidateRunning(UUID candidateId, SectionEntity section) {
       return section
               .getCandidates()
               .stream()
               .map(CandidateEntity::getId)
               .anyMatch(runningId -> runningId.equals(candidateId));
    }

    private Optional<SectionEntity> findSection(UUID sectionId) {
        return sectionRepository.findById(sectionId);
    }
}
