package com.wendersonp.voting.domain.repository;

import com.wendersonp.voting.domain.model.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ICandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    @Query("SELECT count(candidate) FROM CandidateEntity candidate WHERE candidate.id IN :ids")
    int countCandidatesById(@Param("ids") Set<UUID> ids);
}
