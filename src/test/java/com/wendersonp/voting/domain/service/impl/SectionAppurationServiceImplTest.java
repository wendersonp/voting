package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.fixture.CandidateEntityFixture;
import com.wendersonp.voting.domain.fixture.SectionEntityFixture;
import com.wendersonp.voting.domain.fixture.VoteEntityFixture;
import com.wendersonp.voting.domain.fixture.VoterEntityFixture;
import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.SectionReportEntity;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.util.Util;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class SectionAppurationServiceImplTest {

    @InjectMocks
    private SectionAppurationServiceImpl sectionAppurationService;

    @ParameterizedTest
    @MethodSource("testCases")
    void testVoteCounting(List<Integer> voteCountOfEachCandidate, Integer winnerVoteCount) {
        Set<VoteEntity> votes = new HashSet<>();
        SectionEntity section;

        voteCountOfEachCandidate.forEach(voteCount -> {
            var candidate = CandidateEntityFixture.build();
            votes.addAll(Util.generateSet(
                    voteCount, () -> VoteEntityFixture.build(
                            null,
                                VoterEntityFixture.build(),
                                candidate
                            )
            ));
        });
        Set<CandidateEntity> candidateEntitySet = votes
                .stream()
                .map(VoteEntity::getCandidate)
                .collect(Collectors.toSet());

        Set<CandidateEntity> candidatesWithZeroVotes = voteCountOfEachCandidate
                .stream()
                .filter(voteCount -> voteCount == 0)
                .map(voteCount -> CandidateEntityFixture.build())
                .collect(Collectors.toSet());

        candidateEntitySet.addAll(candidatesWithZeroVotes);

        section = SectionEntityFixture.buildToAppurate(candidateEntitySet, votes);

        SectionReportEntity result = sectionAppurationService.countVotes(section);
        Integer actualWinnerVoteCount = Objects.nonNull(result.getWinner()) ?
                result.getVoteCountMap().get(result.getWinner()).intValue() : null;

        assertEquals(winnerVoteCount, actualWinnerVoteCount);
        assertEquals(voteCountOfEachCandidate.size(), result.getVoteCountMap().size());
        result.getVoteCountMap().values().forEach(expectedVoteCount ->
                assertTrue(voteCountOfEachCandidate.contains(expectedVoteCount.intValue()))
        );
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(List.of(16, 30, 5), 30),
                Arguments.of(List.of(42, 121, 44, 3), 121),
                Arguments.of(List.of(1, 0), null),
                Arguments.of(List.of(33, 33, 22), null),
                Arguments.of(List.of(21, 15, 3), 21)
        );
    }

}