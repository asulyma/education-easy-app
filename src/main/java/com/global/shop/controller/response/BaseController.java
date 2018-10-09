package com.global.shop.controller.response;

import com.global.shop.exception.BadRequestParametersRuntimeException;
import com.global.shop.exception.NotAllowedRuntimeException;
import com.global.shop.exception.NotFoundRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Component
public class BaseController {

    @ExceptionHandler(NotFoundRuntimeException.class)
    public BaseResponse handleNotFoundRuntimeException(NotFoundRuntimeException e) {
        return BaseResponse.buildErrorResponse(404, e);
    }

    @ExceptionHandler(NotAllowedRuntimeException.class)
    public BaseResponse handleNotAllowedRuntimeException(NotAllowedRuntimeException e) {
        return BaseResponse.buildErrorResponse(500, e);
    }

    @ExceptionHandler(BadRequestParametersRuntimeException.class)
    public BaseResponse handleBadRequestRuntimeException(BadRequestParametersRuntimeException e) {
        return BaseResponse.buildErrorResponse(400, e);
    }
}
