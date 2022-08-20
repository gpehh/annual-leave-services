package com.ykb.annualleave.annualleaveservices.repository;

import com.ykb.annualleave.annualleaveservices.entity.VacationDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VacationDayRepository extends JpaRepository<VacationDay, Long> {
    @Query("select p from VacationDay p where p.vacationDayDate = ?1")
    VacationDay findByVacationDayDate(String date);
}
