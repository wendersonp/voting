package com.wendersonp.voting.domain.repository;

import com.wendersonp.voting.domain.model.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISectionRepository extends JpaRepository<SectionEntity, UUID> {
}
