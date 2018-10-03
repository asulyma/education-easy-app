package com.global.shop.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Slf4j
@Getter
@Setter
public class NotAllowedRuntimeException extends RuntimeException {

    private String message;

    public NotAllowedRuntimeException(String message) {
        super(message);
        this.message = message;
        log.error(message);
    }

    public NotAllowedRuntimeException() {
        super();
    }
}
