package com.ykb.annualleave.annualleaveservices.repository;

import com.ykb.annualleave.annualleaveservices.entity.PersonalAnnualLeave;
import com.ykb.annualleave.annualleaveservices.entity.VacationDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnualLeaveRepository extends JpaRepository<PersonalAnnualLeave, Long> {
    @Query("select l from PersonalAnnualLeave l where l.employeeId = ?1 and l.approve=3")
    public List<PersonalAnnualLeave> findByEmployeeIdRemainingLeaves(Long employeeId);

    @Query("select l from PersonalAnnualLeave l where l.employeeId = ?1 and l.approve=3")
    public PersonalAnnualLeave findByEmployeeId(Long employeeId);

}
