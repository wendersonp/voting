package com.wendersonp.voting.application.dto;

import com.wendersonp.voting.domain.model.SectionEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record OpenSectionDTO(
        @NotEmpty Set<UUID> candidatesRunningIds,
        @NotNull UUID runningPosition
) {

    public SectionEntity toEntity() {
        return new SectionEntity(
                candidatesRunningIds,
                runningPosition
        );
    }
}
