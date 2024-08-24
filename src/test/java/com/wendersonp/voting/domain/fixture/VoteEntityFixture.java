package com.wendersonp.voting.domain.fixture;

import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.domain.model.VoterEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class VoteEntityFixture {

    public static VoteEntity build(SectionEntity section, VoterEntity voter, CandidateEntity candidate) {
        return new VoteEntity(
                UUID.randomUUID(),
                section,
                candidate,
                voter,
                LocalDateTime.now()
        );
    }
}
