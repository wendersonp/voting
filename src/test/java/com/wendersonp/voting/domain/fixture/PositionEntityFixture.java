package com.wendersonp.voting.domain.fixture;

import com.wendersonp.voting.domain.model.PositionEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class PositionEntityFixture {
    public static PositionEntity build() {
        return new PositionEntity(
                UUID.randomUUID(),
                "Position " + RandomStringUtils.randomAlphabetic(6)
        );
    }
}
