package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.SectionReportEntity;
import com.wendersonp.voting.domain.service.ISectionAppurationService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SectionAppurationServiceImpl implements ISectionAppurationService {


    @Override
    public SectionReportEntity countVotes(SectionEntity section) {
        if (theresOnlyOneVote(section)) {
            section.setVotes(Collections.emptySet());
        }
        Map<CandidateEntity, BigInteger> voteCountMap = generateVoteCountMap(section);
        var winner = calculateWinner(voteCountMap);
        BigInteger totalVotes = calculateTotalVotes(voteCountMap);

        return new SectionReportEntity(
                section,
                voteCountMap,
                Objects.nonNull(winner) ? winner.getKey() : null,
                totalVotes
        );
    }

    private static BigInteger calculateTotalVotes(Map<CandidateEntity, BigInteger> voteCountMap) {
        return voteCountMap.values().stream().reduce(
                BigInteger.ZERO, BigInteger::add);
    }

    private Map.Entry<CandidateEntity, BigInteger> calculateWinner(Map<CandidateEntity, BigInteger> voteCountMap) {
        var winner = voteCountMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).orElse(null);

        if (isThereAnotherWinner(winner, voteCountMap)) {
            winner = null;
        }
        return winner;
    }

    private static Map<CandidateEntity, BigInteger> generateVoteCountMap(SectionEntity section) {
        Map<CandidateEntity, BigInteger> voteCountMap = section
                .getCandidates()
                .stream().collect(Collectors.toMap(Function.identity(), candidate -> BigInteger.ZERO));

        section.getVotes().forEach(vote -> {
            BigInteger voteCountPlusOne = voteCountMap.get(vote.getCandidate()).add(BigInteger.ONE);
            voteCountMap.put(vote.getCandidate(), voteCountPlusOne);
        });
        return voteCountMap;
    }

    private boolean isThereAnotherWinner(
            Map.Entry<CandidateEntity, BigInteger> winner,
            Map<CandidateEntity, BigInteger> voteCountMap
    ) {
        if (Objects.nonNull(winner)) {
            BigInteger winnerVoteCount = winner.getValue();

            long drawCandidates = voteCountMap.values().stream()
                    .filter(voteCount -> Objects.equals(winnerVoteCount, voteCount)
                    ).count();

            return drawCandidates > 1;
        }
        return true;
    }

    private boolean theresOnlyOneVote(SectionEntity section) {
        return section.getVotes().size() == 1;
    }

}
