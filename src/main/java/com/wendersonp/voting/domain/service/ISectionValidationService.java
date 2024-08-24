package com.wendersonp.voting.domain.service;

import com.wendersonp.voting.domain.model.SectionEntity;

import java.util.Set;
import java.util.UUID;

public interface ISectionValidationService {

    boolean validateToOpenSection(Set<UUID> candidateIds, UUID positionId);

    boolean validateToCloseSection(SectionEntity section);

    boolean validateAppurateSection(SectionEntity section);
}
