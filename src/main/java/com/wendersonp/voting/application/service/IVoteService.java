package com.wendersonp.voting.application.service;

import com.wendersonp.voting.application.dto.VoteDTO;

import java.util.UUID;

public interface IVoteService {

    void registerVote(UUID voterId, VoteDTO vote);
}
