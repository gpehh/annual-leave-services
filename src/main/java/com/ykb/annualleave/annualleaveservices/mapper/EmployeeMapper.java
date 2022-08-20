package com.ykb.annualleave.annualleaveservices.mapper;

import com.ykb.annualleave.annualleaveservices.dto.EmployeeDto;
import com.ykb.annualleave.annualleaveservices.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto mapToEmployeeDto(Employee employee);

    Employee mapToEmployee(EmployeeDto employeeDto);
}
