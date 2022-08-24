package com.ykb.annualleave.annualleaveservices.controller;

import com.ykb.annualleave.annualleaveservices.dto.BaseAnnualLeaveRequest;
import com.ykb.annualleave.annualleaveservices.dto.BaseRequest;
import com.ykb.annualleave.annualleaveservices.dto.BaseResponse;
import com.ykb.annualleave.annualleaveservices.dto.PersonalAnnualLeaveDto;
import com.ykb.annualleave.annualleaveservices.entity.DeservedAnnualLeave;
import com.ykb.annualleave.annualleaveservices.entity.PersonalAnnualLeave;
import com.ykb.annualleave.annualleaveservices.entity.Employee;
import com.ykb.annualleave.annualleaveservices.enums.ApproveType;
import com.ykb.annualleave.annualleaveservices.service.AnnualLeaveService;
import com.ykb.annualleave.annualleaveservices.service.DeservedAnnualLeaveService;
import com.ykb.annualleave.annualleaveservices.service.EmployeeService;
import com.ykb.annualleave.annualleaveservices.service.VacationDayService;
import com.ykb.annualleave.annualleaveservices.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/manager-approves")
public class AnnualLeaveManagerController {

    @Autowired
    AnnualLeaveService annualLeaveService;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    VacationDayService vacationDayService;

    @Autowired
    DeservedAnnualLeaveService deservedAnnualLeaveService;


    @PostMapping
    public ResponseEntity<BaseResponse> createAnnualLeave(@RequestBody BaseAnnualLeaveRequest baseRequest) throws ParseException {

        Optional<Employee> employee = employeeService.getEmployee(baseRequest.getEmployeeId());
        PersonalAnnualLeave annualLeave = annualLeaveService.personalUsedAnnualLeave(baseRequest.getEmployeeId());

        double izinGunSayisi = DateUtils.getWorkingAnnualLeaveDayNumber(annualLeave.getAnnualLeaveStartDate().toString(),annualLeave.getAnnualLeaveFinishDate().toString());

        /*HAFTASONU VE RESMI TATILLER TALEP EDILEN GUN SAYISINDAN DÜŞER*/
        Integer isWeekendDaysCount = DateUtils.isWeekend(annualLeave.getAnnualLeaveStartDate(),annualLeave.getAnnualLeaveFinishDate());
        Integer isVacationDaysCount =  vacationDayService.getVacation(annualLeave.getAnnualLeaveStartDate().toString(), annualLeave.getAnnualLeaveFinishDate().toString());

        if( isVacationDaysCount >0)
            izinGunSayisi = izinGunSayisi - isVacationDaysCount;

        if(isWeekendDaysCount>0 )
            izinGunSayisi = izinGunSayisi - isWeekendDaysCount;


        /*YENI İŞE BAŞLAYAN 1 yıldan az 5 gün avans kullandır*/

        if(employee.isPresent()){
            if(!DateUtils.isWorkingFirstYear(employee.get().getJobStartDate())){
                Double annualLeaveAdvanceCount = annualLeaveService.personalUsedAnnualLeaveAdvanceCount(baseRequest.getEmployeeId());
                if(annualLeaveAdvanceCount > 5 || izinGunSayisi > 5){
                    annualLeaveService.updateAnnualLeave(annualLeave.getId(), izinGunSayisi, ApproveType.REJECTED.getCode());
                    return ResponseEntity.ok(new BaseResponse("1 yıldan az çalışma süresi 5 güne kadar avans gün kullanılabilir.",ApproveType.REJECTED.getName()));
                }
                else{
                    annualLeaveService.updateAnnualLeave(annualLeave.getId(), izinGunSayisi, ApproveType.APPROVED.getCode());
                    return ResponseEntity.ok(new BaseResponse("Request Success",ApproveType.APPROVED.getName()));

                }
            }
        }

        /*ILERI TARIHLI IZIN ICIN KALAN IZIN GUN SAYISINDAN DÜŞER kullanılan yıllık izin kaydedilir.*/
        if(Objects.nonNull(annualLeave)){
            DeservedAnnualLeave deservedAnnualLeave = deservedAnnualLeaveService.getAnnualLeave(baseRequest.getEmployeeId());
            if(annualLeaveService.personalUsedAnnualLeaveCount(baseRequest.getEmployeeId()) + izinGunSayisi >deservedAnnualLeave.getDeservedAnnualLeave()){
                return ResponseEntity.ok(new BaseResponse("talep edilen izin hak edilen izin gun sayısını aşmaktadır.",ApproveType.REJECTED.getName()));
            }
            else{
                PersonalAnnualLeave  personalAnnualLeave= annualLeaveService.personalUsedAnnualLeave(employee.get().getId());
                personalAnnualLeave.setEmployeeId(employee.get().getId());
                personalAnnualLeave.setUsedAnnualLeave(izinGunSayisi);
                personalAnnualLeave.setAnnualLeaveStartDate(annualLeave.getAnnualLeaveStartDate());
                personalAnnualLeave.setAnnualLeaveFinishDate(annualLeave.getAnnualLeaveFinishDate());
                personalAnnualLeave.setTransactionDate(LocalDate.now());
                personalAnnualLeave.setApprove(ApproveType.APPROVED.getCode());
                PersonalAnnualLeave dto = annualLeaveService.create(personalAnnualLeave);

                DeservedAnnualLeave deservedAnnualLeaveRecord = deservedAnnualLeaveService.getAnnualLeave(employee.get().getId());

                deservedAnnualLeaveRecord.setEmployeeId(employee.get().getId());
                deservedAnnualLeaveRecord.setDeservedAnnualLeave(deservedAnnualLeave.getDeservedAnnualLeave() - izinGunSayisi);
                deservedAnnualLeaveService.createDeservedAnnualLeave(deservedAnnualLeaveRecord);
                return ResponseEntity.ok(new BaseResponse("Request Success.",ApproveType.APPROVED.getName()));



            }
        }
        return ResponseEntity.ok(new BaseResponse("Bir hata oluştu",""));

    }

}
