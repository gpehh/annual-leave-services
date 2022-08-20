package com.ykb.annualleave.annualleaveservices.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave")
@Getter
@Setter
public class PersonalAnnualLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "ANNUAL_LEAVE_START_DATE")
    private LocalDate annualLeaveStartDate;

    @Column(name = "ANNUAL_LEAVE_FINISH_DATE")
    private LocalDate annualLeaveFinishDate;

    @Column(name = "APPROVE")
    private Integer approve;

    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;

    @Column(name = "USED_ANNUAL_LEAVE")//kullanılan yıllık izin
    private Double usedAnnualLeave;


    @Column(name = "ADVANCE") //avans
    private Double advance;
}
