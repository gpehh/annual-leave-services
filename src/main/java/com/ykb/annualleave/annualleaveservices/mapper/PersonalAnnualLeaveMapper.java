package com.ykb.annualleave.annualleaveservices.mapper;

import com.ykb.annualleave.annualleaveservices.dto.PersonalAnnualLeaveDto;
import com.ykb.annualleave.annualleaveservices.entity.PersonalAnnualLeave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonalAnnualLeaveMapper {

    PersonalAnnualLeaveMapper INSTANCE = Mappers.getMapper(PersonalAnnualLeaveMapper.class);

    PersonalAnnualLeaveDto mapToPersonalAnnualLeaveDto(PersonalAnnualLeave personalAnnualLeave);

    PersonalAnnualLeave mapToPersonalAnnualLeave(PersonalAnnualLeaveDto personalAnnualLeaveDto);
}
