package com.ykb.annualleave.annualleaveservices.service;

import com.ykb.annualleave.annualleaveservices.dto.EmployeeDto;
import com.ykb.annualleave.annualleaveservices.entity.DeservedAnnualLeave;
import com.ykb.annualleave.annualleaveservices.entity.Employee;
import com.ykb.annualleave.annualleaveservices.entity.PersonalAnnualLeave;
import com.ykb.annualleave.annualleaveservices.repository.DeservedAnnualLeaveRepository;
import com.ykb.annualleave.annualleaveservices.repository.EmployeeRepository;
import com.ykb.annualleave.annualleaveservices.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class DeservedAnnualLeaveService {

    @Autowired
    DeservedAnnualLeaveRepository deservedAnnualLeaveRepository;
    public Optional<DeservedAnnualLeave> getAnnualLeave(Long id){
        return deservedAnnualLeaveRepository.findById(id);
    }

    /*public Double getDeserved(long id){
        List<DeservedAnnualLeave> deservedAnnualLeaves= deservedAnnualLeaveRepository.findByDeservedAnnualLeave(id);
        Double deservedLeaveCount=0d;
        for (DeservedAnnualLeave item: deservedAnnualLeaves){
            deservedLeaveCount = deservedLeaveCount + item.getDeservedAnnualLeave();
        }
        return deservedLeaveCount;
    }*/



    public DeservedAnnualLeave createDeservedAnnualLeave(DeservedAnnualLeave deservedAnnualLeave){
        DeservedAnnualLeave saveDeservedAnnualLeave=null;
        DeservedAnnualLeave deservedAnnualLeaves = deservedAnnualLeaveRepository.findByDeservedAnnualLeave(deservedAnnualLeave.getEmployeeId());

        if(Objects.nonNull(deservedAnnualLeaves)) {
            deservedAnnualLeaves.setDeservedAnnualLeave(deservedAnnualLeave.getDeservedAnnualLeave());
            deservedAnnualLeaves.setEmployeeId(deservedAnnualLeave.getEmployeeId());
             saveDeservedAnnualLeave = deservedAnnualLeaveRepository.save(deservedAnnualLeaves);
        }
        else{
            saveDeservedAnnualLeave = deservedAnnualLeaveRepository.save(deservedAnnualLeave);

        }
        return saveDeservedAnnualLeave;
    }
    public Integer deservedAnnualLeaveDay(EmployeeDto employeeDto){
        Integer deservedAnnualLeaveDay=0;
        long workingYear = DateUtils.getWorkingYear(employeeDto.getJobStartDate().toString(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()).toString());
        if(workingYear >= 1 && workingYear <= 5){
            deservedAnnualLeaveDay = 15;
        }
        else if (workingYear >= 5 && workingYear <= 10){
            deservedAnnualLeaveDay = 18;
        }
        else
            deservedAnnualLeaveDay = 24;

        return deservedAnnualLeaveDay;
    }
}
