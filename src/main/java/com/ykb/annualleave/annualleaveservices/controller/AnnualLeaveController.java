package com.ykb.annualleave.annualleaveservices.controller;


import com.ykb.annualleave.annualleaveservices.dto.BaseRequest;
import com.ykb.annualleave.annualleaveservices.dto.BaseResponse;
import com.ykb.annualleave.annualleaveservices.dto.PersonalAnnualLeaveDto;
import com.ykb.annualleave.annualleaveservices.entity.Employee;
import com.ykb.annualleave.annualleaveservices.entity.PersonalAnnualLeave;
import com.ykb.annualleave.annualleaveservices.enums.ApproveType;
import com.ykb.annualleave.annualleaveservices.exception.LocalizedException;
import com.ykb.annualleave.annualleaveservices.service.AnnualLeaveService;
import com.ykb.annualleave.annualleaveservices.service.DeservedAnnualLeaveService;
import com.ykb.annualleave.annualleaveservices.service.EmployeeService;
import com.ykb.annualleave.annualleaveservices.service.VacationDayService;
import com.ykb.annualleave.annualleaveservices.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/annual-leave")
public class AnnualLeaveController {

    @Autowired
    AnnualLeaveService annualLeaveService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    VacationDayService vacationDayService;

    @PostMapping
    public ResponseEntity<BaseResponse> createAnnualLeaveManage(@RequestBody PersonalAnnualLeaveDto annualLeaveDto) throws LocalizedException {

        Double personalUsedAnnualLeaveCount = annualLeaveService.personalUsedAnnualLeaveCount(annualLeaveDto.getId());
        Double deservedAnnualLeaveDayCount = employeeService.deservedAnnualLeaveDay(annualLeaveDto.getId());
        double izinGunSayisi = 0;
        try {
            izinGunSayisi = DateUtils.getWorkingAnnualLeaveDayNumber(annualLeaveDto.getAnnualLeaveStartDate().toString(), annualLeaveDto.getAnnualLeaveFinishDate().toString());
        } catch (ParseException e) {
            throw new LocalizedException("message.parse_exception", Locale.US);
        }
        /*avans case*/
        if (deservedAnnualLeaveDayCount.equals(0.0)) {

            Optional<Employee> employee = employeeService.getEmployee(annualLeaveDto.getId());
            Optional<PersonalAnnualLeave> annualLeave = annualLeaveService.getAnnualLeave(employee.get().getId());

            if (employee.isPresent()) {
                if (!DateUtils.isWorkingFirstYear(employee.get().getJobStartDate())) {
                    PersonalAnnualLeaveDto personalAnnualLeave = new PersonalAnnualLeaveDto();
                    personalAnnualLeave.setEmployeeId(annualLeaveDto.getId());
                    personalAnnualLeave.setAnnualLeaveStartDate(annualLeaveDto.getAnnualLeaveStartDate());
                    personalAnnualLeave.setAnnualLeaveFinishDate(annualLeaveDto.getAnnualLeaveFinishDate());
                    personalAnnualLeave.setApprove(ApproveType.WAITING_APPROVE.getCode());
                    PersonalAnnualLeaveDto dto = annualLeaveService.createAnnualLeave(personalAnnualLeave);
                    if (Objects.nonNull(dto)) {
                        return ResponseEntity.ok(new BaseResponse("Başvurunuz Alındı. Yeni işe başlayan kişiler avans olarak 5 iş günü kadar izin kullanabilir. Bu uygulama sadece ilk yıl için\n" +
                                "geçerlidir", ApproveType.WAITING_APPROVE.getName(), new BaseRequest(employee.get().getId())));
                    }
                }
            }
        }
        else {
            PersonalAnnualLeaveDto personalAnnualLeave = new PersonalAnnualLeaveDto();
            personalAnnualLeave.setEmployeeId(annualLeaveDto.getId());
            personalAnnualLeave.setAnnualLeaveStartDate(annualLeaveDto.getAnnualLeaveStartDate());
            personalAnnualLeave.setAnnualLeaveFinishDate(annualLeaveDto.getAnnualLeaveFinishDate());
            personalAnnualLeave.setApprove(ApproveType.WAITING_APPROVE.getCode());
            PersonalAnnualLeaveDto dto = annualLeaveService.createAnnualLeave(personalAnnualLeave);
            if (Objects.nonNull(dto)) {
                return ResponseEntity.ok(new BaseResponse("Başvurunuz Alındı.", ApproveType.WAITING_APPROVE.getName(), new BaseRequest(annualLeaveDto.getId())));
            }
        }
        return ResponseEntity.ok(new BaseResponse("Bir hata oluştu. Tekrar deneyiniz", ""));
    }



}
