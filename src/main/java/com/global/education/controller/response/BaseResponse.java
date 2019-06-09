package com.global.education.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class using for building response for front-end side. For other objects - this class as wrapper, which contains
 * field 'data' and other useful metadata.
 */
@Data
@NoArgsConstructor
public class BaseResponse<T> {

    private Boolean success = true;

    private int responseCode = 200;

    private T data;

    private String errorCode;

    private String errorMessage;

    public BaseResponse(T data) {
        this.data = data;
    }

    static BaseResponse buildErrorResponse(int responseCode, Exception e) {

        BaseResponse response = new BaseResponse();
        response.setResponseCode(responseCode);
        response.setErrorMessage(e.getMessage());

        return response;
    }
}
