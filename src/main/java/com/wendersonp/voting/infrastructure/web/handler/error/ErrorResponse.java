package com.wendersonp.voting.infrastructure.web.handler.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record ErrorResponse(
        @JsonProperty("codigo")
        int code,
        @JsonProperty("mensagem")
        String message,
        @JsonProperty("momento")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStamp) {
}
