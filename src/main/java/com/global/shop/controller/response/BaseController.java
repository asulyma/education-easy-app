package com.global.shop.controller.response;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Component
public class BaseController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public BaseResponse handleResourceNotFound(ResourceNotFoundException e) {
        return BaseResponse.buildErrorResponse(404, e);
    }

    //TODO refactoring
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse handleRuntimeException(RuntimeException e){
        return BaseResponse.buildErrorResponse(500, e);
    }
}