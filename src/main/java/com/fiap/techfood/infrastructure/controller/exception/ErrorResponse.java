package com.fiap.techfood.infrastructure.controller.exception;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class ErrorResponse {

    private final OffsetDateTime timestamp;
    private final String messageError;

    public ErrorResponse(String messageError) {
        this.timestamp = OffsetDateTime.now();
        this.messageError = messageError;
    }
}
