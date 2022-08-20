package com.ykb.annualleave.annualleaveservices.controller;

import com.ykb.annualleave.annualleaveservices.dto.EmployeeDto;
import com.ykb.annualleave.annualleaveservices.entity.DeservedAnnualLeave;
import com.ykb.annualleave.annualleaveservices.service.DeservedAnnualLeaveService;
import com.ykb.annualleave.annualleaveservices.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DeservedAnnualLeaveService deservedAnnualLeaveService;


    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee (@RequestBody EmployeeDto employee)  {

        EmployeeDto employees = employeeService.createEmployee(employee);

        Double deservedAnnualLeaveDayCount = employeeService.deservedAnnualLeaveDay(employee.getId());
        DeservedAnnualLeave deservedAnnualLeave = new DeservedAnnualLeave();
        deservedAnnualLeave.setDeservedAnnualLeave(deservedAnnualLeaveDayCount);
        deservedAnnualLeave.setEmployeeId(employees.getId());
        deservedAnnualLeaveService.createDeservedAnnualLeave(deservedAnnualLeave);

        return ResponseEntity.ok(employees);
    }

}
