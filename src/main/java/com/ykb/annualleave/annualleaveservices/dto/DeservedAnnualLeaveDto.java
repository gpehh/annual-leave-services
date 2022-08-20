package com.ykb.annualleave.annualleaveservices.dto;

import lombok.Data;


@Data
public class DeservedAnnualLeaveDto {

    private Long id;

    private Long employeeId;

    private Double deservedAnnualLeave;

    private String year;
}
