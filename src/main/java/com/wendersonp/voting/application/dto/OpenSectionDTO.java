package com.wendersonp.voting.application.dto;

import com.wendersonp.voting.application.util.ErrorMessages;
import com.wendersonp.voting.domain.model.SectionEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record OpenSectionDTO(
        @NotEmpty(message = ErrorMessages.FIELD_CANNOT_BE_EMPTY) Set<UUID> candidatesRunningIds,
        @NotNull(message = ErrorMessages.FIELD_CANNOT_BE_NULL) UUID runningPosition
) {

    public SectionEntity toEntity() {
        return new SectionEntity(
                candidatesRunningIds,
                runningPosition
        );
    }
}
