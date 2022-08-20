package com.ykb.annualleave.annualleaveservices.service;

import com.ykb.annualleave.annualleaveservices.entity.VacationDay;
import com.ykb.annualleave.annualleaveservices.repository.VacationDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class VacationDayService {

    @Autowired
    VacationDayRepository vacationDayRepository;


    public Integer getVacation(String localDate, String endDate){
        Integer vacationDayCount = 0;

        VacationDay byVacationDayDate = vacationDayRepository.findByVacationDayDate(localDate);
        VacationDay byVacationDayEndDate = vacationDayRepository.findByVacationDayDate(endDate);

        if (Objects.nonNull(byVacationDayDate)){
            if(byVacationDayDate.getId()!=null){
                vacationDayCount++;
            }
        }

        if (Objects.nonNull(byVacationDayEndDate)){
            if(byVacationDayEndDate.getId()!=null){
                vacationDayCount++;
            }
        }
        return vacationDayCount;
    }
}
