package com.global.education.controller.handler;

import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class using as parent for all controller classes. Each class, which marks as 'Controller' - extends this class.
 * The main logic - catching exceptions (in more time - customs runtime exceptions).
 */
@Component
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
    private static class BaseResponse {

        private int responseCode = 200;
        private String errorMessage;
        private boolean success = true;

        static BaseResponse buildErrorResponse(int responseCode, Exception e) {
            BaseResponse response = new BaseResponse();
            response.setResponseCode(responseCode);
            response.setErrorMessage(e.getMessage());
            response.setSuccess(false);
            return response;
        }
    }
}
