package com.wendersonp.voting.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wendersonp.voting.domain.model.PositionEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PositionDTO(UUID id,
                          @JsonProperty("nome")
                          @Size(max = 20)
                          @NotBlank String name) {

    public PositionDTO(PositionEntity entity) {
        this(entity.getId(), entity.getName());
    }

    public PositionEntity toEntity() {
        return new PositionEntity(null, name);
    }
}
