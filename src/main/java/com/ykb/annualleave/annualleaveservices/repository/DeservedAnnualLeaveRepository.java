package com.ykb.annualleave.annualleaveservices.repository;

import com.ykb.annualleave.annualleaveservices.entity.DeservedAnnualLeave;
import com.ykb.annualleave.annualleaveservices.entity.PersonalAnnualLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DeservedAnnualLeaveRepository extends JpaRepository<DeservedAnnualLeave, Long> {
    @Query("from DeservedAnnualLeave l where l.employeeId = :employeeId")
    public DeservedAnnualLeave findByDeservedAnnualLeave(Long employeeId);


}
