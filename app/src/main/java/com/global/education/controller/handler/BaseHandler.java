package com.global.education.controller.handler;

import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class using as parent for all controller classes. Each class, which marks as 'Controller' - extends this class.
 * The main logic - catching exceptions (in more time - customs runtime exceptions).
 */
@ControllerAdvice
public class BaseHandler {

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

    @Getter
    @Setter
    public static class BaseResponse {

        private int code = 200;
        private String message;
        private boolean success = true;

        static BaseResponse buildErrorResponse(int responseCode, Exception e) {
            BaseResponse response = new BaseResponse();
            response.setCode(responseCode);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            return response;
        }
    }
}
