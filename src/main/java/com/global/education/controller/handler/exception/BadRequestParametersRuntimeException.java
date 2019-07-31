package com.global.education.controller.handler.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The custom runtime exception. Using when front-end side sent to back-end incorrect data (incorrect DTO).
 */
@Slf4j
@Getter
@Setter
public class BadRequestParametersRuntimeException extends RuntimeException {

    private String message;

    public BadRequestParametersRuntimeException(String message) {
        super(message);
        this.message = message;
        log.error(message);
    }

    public BadRequestParametersRuntimeException() {
        super();
    }

}
