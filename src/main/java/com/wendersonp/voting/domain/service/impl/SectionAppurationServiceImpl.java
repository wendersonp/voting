package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.SectionReportEntity;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.domain.repository.ISectionRepository;
import com.wendersonp.voting.domain.service.ISectionAppurationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SectionAppurationServiceImpl implements ISectionAppurationService {


    @Override
    public SectionReportEntity countVotes(SectionEntity section) {
        ///TODO: Colocar no map os valores das pessoas que nao receberam voto
        Map<CandidateEntity, List<VoteEntity>> votesMap = section
                .getVotes()
                .stream()
                .collect(Collectors.groupingBy(VoteEntity::getCandidate));

        var voteCountMap = votesMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, votesEntry -> BigInteger.valueOf(votesEntry.getValue().size())));

        var winner = voteCountMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        var totalVotes = voteCountMap.values().stream().reduce(
                BigInteger.ZERO, BigInteger::add);
        return new SectionReportEntity(
                section,
                voteCountMap,
                winner.get().getKey(),
                totalVotes
        );
    }
}
