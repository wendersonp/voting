package com.wendersonp.voting.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wendersonp.voting.application.util.ErrorMessages;
import com.wendersonp.voting.domain.model.VoterEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record VoterDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,
        @JsonProperty("nome")
        @NotBlank(message = ErrorMessages.FIELD_CANNOT_BE_EMPTY) String name) {

    public VoterDTO(VoterEntity entity) {
        this(entity.getId(), entity.getName());
    }

    public VoterEntity toEntity() {
        return new VoterEntity(null, name);
    }

    public VoterEntity toEntity(UUID id) {
        return new VoterEntity(id, name);
    }

}
