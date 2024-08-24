package com.wendersonp.voting.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wendersonp.voting.application.util.ErrorMessages;
import com.wendersonp.voting.domain.model.CandidateEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CandidateDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,
        @JsonProperty("nome")
        @Size(max = 20, message = ErrorMessages.FIELD_CANNOT_HAVE_MORE_THAN_20)
        @NotBlank(message = ErrorMessages.FIELD_CANNOT_BE_EMPTY) String name) {

    public CandidateDTO(CandidateEntity entity) {
        this(entity.getId(), entity.getName());
    }

    public CandidateEntity toEntity() {
        return new CandidateEntity(null, name);
    }

    public CandidateEntity toEntity(UUID id) {
        return new CandidateEntity(id, name);
    }

}
