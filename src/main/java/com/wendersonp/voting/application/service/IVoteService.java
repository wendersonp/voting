package com.wendersonp.voting.application.service;

import com.wendersonp.voting.application.dto.VoteDTO;

public interface IVoteService {

    void registerVote(VoteDTO vote);
}
