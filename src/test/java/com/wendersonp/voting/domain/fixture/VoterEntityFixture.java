package com.wendersonp.voting.domain.fixture;

import com.wendersonp.voting.domain.model.VoterEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class VoterEntityFixture {

    public static VoterEntity build() {
        return new VoterEntity(
                UUID.randomUUID(),
                "Voter " + RandomStringUtils.randomAlphabetic(6)
        );
    }
}
