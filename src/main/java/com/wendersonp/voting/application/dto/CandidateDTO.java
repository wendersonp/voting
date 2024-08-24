package com.wendersonp.voting.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wendersonp.voting.domain.model.CandidateEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CandidateDTO(
        UUID id,
        @JsonProperty("nome")
        @Size(max = 20)
        @NotBlank String name) {

    public CandidateDTO(CandidateEntity entity) {
        this(entity.getId(), entity.getName());
    }

    public CandidateEntity toEntity() {
        return new CandidateEntity(null, name);
    }

}
