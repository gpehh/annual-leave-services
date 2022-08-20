package com.ykb.annualleave.annualleaveservices.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {

      private Long id;
      private String name;
      private String surname;
      private LocalDate jobStartDate;

}
