package com.global.education.controller.handler.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The custom runtime exception. Using when some information was not found for concrete query.
 */
@Slf4j
@Getter
@Setter
public class NotFoundRuntimeException extends RuntimeException {

    private String message;

    public NotFoundRuntimeException(String message) {
        super(message);
        this.message = message;
        log.error(message);
    }

    public NotFoundRuntimeException() {
        super();
    }
}
