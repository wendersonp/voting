package com.wendersonp.voting.application.dto;

import com.wendersonp.voting.application.util.ErrorMessages;
import com.wendersonp.voting.domain.model.VoteEntity;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VoteDTO(
        @NotNull(message = ErrorMessages.FIELD_CANNOT_BE_NULL) UUID sectionId,
        @NotNull(message = ErrorMessages.FIELD_CANNOT_BE_NULL) UUID candidateId) {

    public VoteEntity toEntity(UUID voterId){
        return new VoteEntity(sectionId, candidateId, voterId);
    }
}
