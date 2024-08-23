package com.wendersonp.voting.domain.fixture;

import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.enumeration.SectionStatus;
import com.wendersonp.voting.util.Util;

import java.time.LocalDateTime;

public class SectionEntityFixture {

    public static SectionEntity buildToOpen() {
        return new SectionEntity(
                Util.generateSet(2, CandidateEntityFixture::build),
                PositionEntityFixture.build(),
                null,
                null
        );
    }

    public static SectionEntity buildOpen() {
        return new SectionEntity(
                Util.generateSet(2, CandidateEntityFixture::build),
                PositionEntityFixture.build(),
                SectionStatus.OPEN,
                LocalDateTime.now()
        );
    }
}
