package com.wendersonp.voting.domain.repository;

import com.wendersonp.voting.domain.model.VoterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IVoterRepository extends JpaRepository<VoterEntity, UUID> {
}
