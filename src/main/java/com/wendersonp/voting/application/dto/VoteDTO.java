package com.wendersonp.voting.application.dto;

import com.wendersonp.voting.domain.model.VoteEntity;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VoteDTO(
        @NotNull UUID sectionId,
        @NotNull UUID voterId,
        @NotNull UUID candidateId) {

    public VoteEntity toEntity(){
        return new VoteEntity(sectionId, voterId, candidateId);
    }
}
