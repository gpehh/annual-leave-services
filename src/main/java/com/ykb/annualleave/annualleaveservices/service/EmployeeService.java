package com.ykb.annualleave.annualleaveservices.service;

import com.ykb.annualleave.annualleaveservices.dto.EmployeeDto;
import com.ykb.annualleave.annualleaveservices.entity.Employee;
import com.ykb.annualleave.annualleaveservices.entity.PersonalAnnualLeave;
import com.ykb.annualleave.annualleaveservices.mapper.EmployeeMapper;
import com.ykb.annualleave.annualleaveservices.mapper.PersonalAnnualLeaveMapper;
import com.ykb.annualleave.annualleaveservices.repository.EmployeeRepository;
import com.ykb.annualleave.annualleaveservices.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    public Optional<Employee> getEmployee(long id){
        return employeeRepository.findById(id);
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee annualLeave = employeeMapper.mapToEmployee(employeeDto);

        Employee employee = employeeRepository.save(annualLeave);
        EmployeeDto annualLeaveDto = employeeMapper.mapToEmployeeDto(employee);

        return annualLeaveDto;
    }
    public Double deservedAnnualLeaveDay(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Double deservedAnnualLeaveDay=0d;

        if (employeeOptional.isPresent()){
            long workingYear = DateUtils.getWorkingYear(employeeOptional.get().getJobStartDate().toString(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
            if(workingYear >= 1 && workingYear <= 5){
                deservedAnnualLeaveDay = Double.valueOf(15);
            }
            else if (workingYear >= 5 && workingYear <= 10){
                deservedAnnualLeaveDay = Double.valueOf(18);
            }
            else if (workingYear >= 10)
                deservedAnnualLeaveDay = Double.valueOf(24);
            else
                deservedAnnualLeaveDay = 0d;
        }



        return deservedAnnualLeaveDay;
    }
}
