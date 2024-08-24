package com.wendersonp.voting.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wendersonp.voting.domain.model.VoterEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record VoterDTO(UUID id,
                       @JsonProperty("nome")
                       @NotBlank String name) {

    public VoterDTO(VoterEntity entity) {
        this(entity.getId(), entity.getName());
    }

    public VoterEntity toEntity() {
        return new VoterEntity(null, name);
    }
}
