package com.wendersonp.voting.domain.fixture;

import com.wendersonp.voting.domain.model.CandidateEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class CandidateEntityFixture {

    public static CandidateEntity build() {
        return new CandidateEntity(
                UUID.randomUUID(),
                "Candidate " + RandomStringUtils.randomAlphabetic(6)
        );
    }
}
