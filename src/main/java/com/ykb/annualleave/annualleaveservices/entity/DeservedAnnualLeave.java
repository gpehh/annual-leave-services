package com.ykb.annualleave.annualleaveservices.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "deserved_annual_leave")
@Getter
@Setter
public class DeservedAnnualLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "DESERVED_ANNUAL_LEAVE")
    private Double deservedAnnualLeave; //hak edilen izin

    @Column(name = "ANNUAL_YEAR")
    private String year;
}
