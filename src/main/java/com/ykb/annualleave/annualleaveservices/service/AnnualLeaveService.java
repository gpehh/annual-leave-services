package com.ykb.annualleave.annualleaveservices.service;

import com.ykb.annualleave.annualleaveservices.dto.PersonalAnnualLeaveDto;
import com.ykb.annualleave.annualleaveservices.entity.PersonalAnnualLeave;
import com.ykb.annualleave.annualleaveservices.enums.ApproveType;
import com.ykb.annualleave.annualleaveservices.mapper.PersonalAnnualLeaveMapper;
import com.ykb.annualleave.annualleaveservices.repository.AnnualLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AnnualLeaveService {

    @Autowired
    AnnualLeaveRepository annualLeaveRepository;

    PersonalAnnualLeaveMapper annualLeaveMapper = PersonalAnnualLeaveMapper.INSTANCE;

    public Optional<PersonalAnnualLeave> getAnnualLeave(long id){
        return annualLeaveRepository.findById(id);
    }

    public PersonalAnnualLeaveDto createAnnualLeave(PersonalAnnualLeaveDto personalAnnualLeaveDto) {

        PersonalAnnualLeave annualLeave = annualLeaveMapper.mapToPersonalAnnualLeave(personalAnnualLeaveDto);
        PersonalAnnualLeave savePersonalAnnualLeave = annualLeaveRepository.save(annualLeave);
        PersonalAnnualLeaveDto annualLeaveDto = annualLeaveMapper.mapToPersonalAnnualLeaveDto(savePersonalAnnualLeave);


        return annualLeaveDto;
    }
    public PersonalAnnualLeave create(PersonalAnnualLeave annualLeave) {

        PersonalAnnualLeave savePersonalAnnualLeave = annualLeaveRepository.save(annualLeave);


        return savePersonalAnnualLeave;
    }

    public PersonalAnnualLeaveDto updateAnnualLeave(Long id,Double advance, Integer approveType) {

        Optional<PersonalAnnualLeave> annualLeaveUpdate = annualLeaveRepository.findById(id);
        if(approveType.equals(ApproveType.APPROVED.getCode())){
            annualLeaveUpdate.get().setApprove(ApproveType.APPROVED.getCode());
            annualLeaveUpdate.get().setAdvance(advance);
        }
        else{
            annualLeaveUpdate.get().setApprove(ApproveType.REJECTED.getCode());

        }
        PersonalAnnualLeave savePersonalAnnualLeave= annualLeaveRepository.save(annualLeaveUpdate.get());
        PersonalAnnualLeaveDto annualLeaveDto = annualLeaveMapper.mapToPersonalAnnualLeaveDto(savePersonalAnnualLeave);


        return annualLeaveDto;
    }

    public Double personalUsedAnnualLeaveCount(Long id){
        List<PersonalAnnualLeave> personalAnnualLeaveList = annualLeaveRepository.findByEmployeeIdRemainingLeaves(id);
        Double usedAnnualLeaveCount=0d;
        for(PersonalAnnualLeave item : personalAnnualLeaveList){
            if(Objects.nonNull(item.getUsedAnnualLeave())){
             usedAnnualLeaveCount = usedAnnualLeaveCount + item.getUsedAnnualLeave();
            }
        }
        return usedAnnualLeaveCount;
    }

    public Double personalUsedAnnualLeaveAdvanceCount(Long id){
        List<PersonalAnnualLeave> personalAnnualLeaveList = annualLeaveRepository.findByEmployeeIdRemainingLeaves(id);
        Double usedAnnualLeaveAdvanceCount=0d;
        for(PersonalAnnualLeave item : personalAnnualLeaveList){
            usedAnnualLeaveAdvanceCount = usedAnnualLeaveAdvanceCount + item.getAdvance();
        }
        //test commit1
        return usedAnnualLeaveAdvanceCount;
    }

    public PersonalAnnualLeave personalUsedAnnualLeave(Long id){
        PersonalAnnualLeave personalAnnualLeave = annualLeaveRepository.findByEmployeeId(id);

        return personalAnnualLeave;
    }


}
