package com.ykb.annualleave.annualleaveservices.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vacations")
@Getter
@Setter
public class VacationDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String vacationDayDate;

    @Column(name ="name")
    private String vacationName;
}
