package com.ykb.annualleave.annualleaveservices.dto;

import lombok.Data;


import java.time.LocalDate;

@Data
public class PersonalAnnualLeaveDto {

    private Long id;

    private Long employeeId;

    private LocalDate annualLeaveStartDate;
    private LocalDate annualLeaveFinishDate;

    private Integer approve;

    private LocalDate transactionDate;

    private Double usedAnnualLeave;

    private Double advance;

}
