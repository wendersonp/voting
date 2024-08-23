package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.SectionReportEntity;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.domain.service.ISectionAppurationService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.stream.Collectors;

@Service
public class SectionAppurationServiceImpl implements ISectionAppurationService {


    @Override
    public SectionReportEntity countVotes(SectionEntity section) {
        Map<CandidateEntity, List<VoteEntity>> votesMap = section
                .getVotes()
                .stream()
                .collect(Collectors.groupingBy(VoteEntity::getCandidate));

        Map<CandidateEntity, BigInteger> voteCountMap = votesMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, votesEntry -> BigInteger.valueOf(votesEntry.getValue().size())
                ));

        addCandidatesWithNoVotes(section.getCandidates(), voteCountMap);

        var winner = voteCountMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        BigInteger totalVotes = voteCountMap.values().stream().reduce(
                BigInteger.ZERO, BigInteger::add);
        return new SectionReportEntity(
                section,
                voteCountMap,
                winner.get().getKey(),
                totalVotes
        );
    }

    private void addCandidatesWithNoVotes(Set<CandidateEntity> allCandidates, Map<CandidateEntity, BigInteger> voteCountMap) {
        allCandidates.forEach(candidate ->
            voteCountMap.putIfAbsent(candidate, BigInteger.ZERO)
        );
    }
}
