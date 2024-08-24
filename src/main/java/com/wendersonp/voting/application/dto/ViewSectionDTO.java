package com.wendersonp.voting.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.enumeration.SectionStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ViewSectionDTO(
        UUID id,
        SectionStatus status,
        String position,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

    public ViewSectionDTO(SectionEntity section) {
        this(section.getId(),
                section.getStatus(),
                section.getRunningPosition().getName(),
                section.getStartDate(),
                section.getEndDate()
        );
    }

}
