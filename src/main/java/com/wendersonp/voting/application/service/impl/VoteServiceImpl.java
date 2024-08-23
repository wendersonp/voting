package com.wendersonp.voting.application.service.impl;

import com.wendersonp.voting.application.dto.VoteDTO;
import com.wendersonp.voting.application.exception.BadRequestException;
import com.wendersonp.voting.application.service.IVoteService;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.domain.repository.IVoteRepository;
import com.wendersonp.voting.domain.service.IVoteValidationService;

public class VoteServiceImpl implements IVoteService {

    private final IVoteRepository voteRepository;

    private final IVoteValidationService voteValidationService;

    public VoteServiceImpl(IVoteRepository voteRepository, IVoteValidationService voteValidationService) {
        this.voteRepository = voteRepository;
        this.voteValidationService = voteValidationService;
    }

    @Override
    public void registerVote(VoteDTO voteDTO) {
        VoteEntity vote = voteDTO.toEntity();

        if (voteValidationService.isVoteValid(vote)) {
            throw new BadRequestException();
        }

        voteRepository.save(vote);
    }
}
