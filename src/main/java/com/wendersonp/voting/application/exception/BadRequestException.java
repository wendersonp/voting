package com.wendersonp.voting.application.exception;

public class BadRequestException extends IllegalArgumentException{
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException() {
    }

    public BadRequestException(String s) {
        super(s);
    }
}
