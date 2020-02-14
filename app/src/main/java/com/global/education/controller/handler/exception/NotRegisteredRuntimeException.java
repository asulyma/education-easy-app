package com.global.education.controller.handler.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The custom runtime exception. Using when some part of logic are not allowed for concrete user\entity.
 */
@Slf4j
@Getter
@Setter
public class NotRegisteredRuntimeException extends RuntimeException {

    private String message;

    public NotRegisteredRuntimeException(String message) {
        super(message);
        this.message = message;
        log.error(message);
    }

    public NotRegisteredRuntimeException() {
        super();
    }
}
