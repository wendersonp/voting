package com.wendersonp.voting.domain.service;

import com.wendersonp.voting.domain.model.VoteEntity;

public interface IVoteValidationService {

    boolean isVoteValid(VoteEntity vote);
}
