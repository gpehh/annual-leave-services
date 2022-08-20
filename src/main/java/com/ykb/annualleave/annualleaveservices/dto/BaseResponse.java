package com.ykb.annualleave.annualleaveservices.dto;

import lombok.Data;

@Data
public class BaseResponse {

    private String message;
    private String approve;
    private BaseRequest baseRequest;

    public BaseResponse(String message, String approve,BaseRequest baseRequest) {
        this.message = message;
        this.approve = approve;
        this.baseRequest=baseRequest;
    }
    public BaseResponse(String message, String approve) {
        this.message = message;
        this.approve = approve;
    }
}
