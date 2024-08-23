package com.wendersonp.voting.domain.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


public class SectionReportEntity {

    private SectionEntity sectionEntity;

    private Map<CandidateEntity, BigInteger> voteCountMap = new HashMap<>();

    private CandidateEntity winner;

    private BigInteger totalVotes;

    public SectionReportEntity(SectionEntity sectionEntity, Map<CandidateEntity, BigInteger> voteCountMap, CandidateEntity winner, BigInteger totalVotes) {
        this.sectionEntity = sectionEntity;
        this.voteCountMap = voteCountMap;
        this.winner = winner;
        this.totalVotes = totalVotes;
    }

    public SectionReportEntity() {
    }

    public SectionEntity getSectionEntity() {
        return sectionEntity;
    }

    public void setSectionEntity(SectionEntity sectionEntity) {
        this.sectionEntity = sectionEntity;
    }

    public Map<CandidateEntity, BigInteger> getVoteCountMap() {
        return voteCountMap;
    }

    public void setVoteCountMap(Map<CandidateEntity, BigInteger> voteCountMap) {
        this.voteCountMap = voteCountMap;
    }

    public CandidateEntity getWinner() {
        return winner;
    }

    public void setWinner(CandidateEntity winner) {
        this.winner = winner;
    }

    public BigInteger getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(BigInteger totalVotes) {
        this.totalVotes = totalVotes;
    }
}
