package com.ykb.annualleave.annualleaveservices.dto;

import lombok.Data;

@Data
public class BaseRequest {

    private Long employeeId;

    public BaseRequest(Long employeeId) {
        this.employeeId = employeeId;
    }
}
