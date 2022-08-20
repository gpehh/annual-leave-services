package com.ykb.annualleave.annualleaveservices;

import com.ykb.annualleave.annualleaveservices.service.AnnualLeaveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class AnnualLeaveDtoServicesApplicationTests {

    @Autowired
    AnnualLeaveService annualLeaveService;

}
