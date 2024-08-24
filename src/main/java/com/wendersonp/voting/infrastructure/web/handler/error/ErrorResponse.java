package com.wendersonp.voting.infrastructure.web.handler.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        @JsonProperty("codigo")
        int code,
        @JsonProperty("mensagem")
        String message,
        @JsonProperty("momento")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStamp,
        Map<String, String> errors) {

        public ErrorResponse(int code, String message, LocalDateTime timeStamp) {
                this(code, message, timeStamp, null);
        }
}
