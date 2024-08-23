package com.wendersonp.voting.domain.service;

import com.wendersonp.voting.domain.model.SectionEntity;

import java.util.Set;
import java.util.UUID;

public interface ISectionValidationService {

    boolean validateOpenSection(Set<UUID> candidateIds, UUID positionId);

    boolean validateCloseSection(SectionEntity section);
}
