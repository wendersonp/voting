package com.wendersonp.voting.domain.repository;


import com.wendersonp.voting.domain.model.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPositionRepository extends JpaRepository<PositionEntity, UUID> {
}
