package com.ykb.annualleave.annualleaveservices.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ApproveType {

    APPROVED(1,"Approved"),
    REJECTED(2,"REJECTED"),
    WAITING_APPROVE(3,"WaitingApprove");

    private Integer code;
    private String name;
}

