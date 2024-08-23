package com.wendersonp.voting.domain.fixture;

import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.domain.model.enumeration.SectionStatus;
import com.wendersonp.voting.util.Util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class SectionEntityFixture {

    public static SectionEntity buildToOpen() {
        return new SectionEntity(
                Util.generateSet(2, CandidateEntityFixture::build),
                PositionEntityFixture.build(),
                null,
                null
        );
    }

    public static SectionEntity buildToClose() {
        var section = new SectionEntity(
                Util.generateSet(2, CandidateEntityFixture::build),
                PositionEntityFixture.build(),
                SectionStatus.OPEN,
                LocalDateTime.now()
        );
        List<CandidateEntity> candidates = section.getCandidates().stream().toList();
        Set<VoteEntity> votes = Util.generateSet(
                Util.RANDOM.nextInt(2, 100),
                () -> VoteEntityFixture.build(
                        section,
                        VoterEntityFixture.build(),
                        shouldSelectFirstOrSecond() ? candidates.get(0): candidates.get(1)
                ));
        section.setVotes(votes);
        return section;
    }

    public static SectionEntity buildToAppurate(Set<CandidateEntity> candidateEntitySet, Set<VoteEntity> voteEntitySet) {
        var section = new SectionEntity(
                candidateEntitySet,
                PositionEntityFixture.build(),
                SectionStatus.OPEN,
                LocalDateTime.now()
        );
        section.setVotes(voteEntitySet);
        section.closeSection();
        return section;
    }

    private static boolean shouldSelectFirstOrSecond() {
        return Util.RANDOM.nextInt() % 2 == 0;
    }
}
