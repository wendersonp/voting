package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.domain.repository.ISectionRepository;
import com.wendersonp.voting.domain.repository.IVoteRepository;
import com.wendersonp.voting.domain.service.IVoteValidationService;
import org.springframework.stereotype.Service;

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
        return doesVoteNotExist(vote) && isSectionOpen(vote.getSection().getId());
    }

    private boolean doesVoteNotExist(VoteEntity vote) {
        return !voteRepository.existsBySectionIdAndCandidateIdAndVoterId(
                vote.getSection().getId(), vote.getCandidate().getId(), vote.getVoter().getId()
        );
    }

    private boolean isSectionOpen(UUID sectionId) {
        return sectionRepository.findById(sectionId)
                .map(SectionEntity::isOpen).orElse(false);
    }
}
