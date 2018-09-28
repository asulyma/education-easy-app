package com.global.shop.controller.response;

import lombok.Data;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Data
public class BaseResponse<T> {

    private Boolean success = true;

    private int responseCode = 200;

    private T data;

    private String errorCode;

    private String errorMessage;

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse() {
    }

    static BaseResponse buildErrorResponse(int responseCode, Exception e) {

        BaseResponse response = new BaseResponse();
        response.setResponseCode(responseCode);
        response.setErrorMessage(e.getMessage());

        return response;
    }
}
