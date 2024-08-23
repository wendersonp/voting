package com.wendersonp.voting.domain.repository;

import com.wendersonp.voting.domain.model.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IVoteRepository extends JpaRepository<VoteEntity, UUID> {

    boolean existsByCandidateId(UUID candidateId);

    boolean existsByVoterId(UUID voterId);

    boolean existsBySectionIdAndCandidateIdAndVoterId(UUID sectionId, UUID candidateId, UUID voterId);
}
