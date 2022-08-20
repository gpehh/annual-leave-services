package com.ykb.annualleave.annualleaveservices.mapper;

import com.ykb.annualleave.annualleaveservices.dto.DeservedAnnualLeaveDto;
import com.ykb.annualleave.annualleaveservices.dto.EmployeeDto;
import com.ykb.annualleave.annualleaveservices.entity.DeservedAnnualLeave;
import com.ykb.annualleave.annualleaveservices.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeservedAnnualLeaveMapper {
    DeservedAnnualLeaveMapper INSTANCE = Mappers.getMapper(DeservedAnnualLeaveMapper.class);

    DeservedAnnualLeaveDto mapToDeservedAnnualLeaveDto(DeservedAnnualLeave deservedAnnualLeave);

    DeservedAnnualLeave mapToDeservedAnnualLeave(DeservedAnnualLeaveDto deservedAnnualLeaveDto);
}
