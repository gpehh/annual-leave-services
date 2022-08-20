package com.ykb.annualleave.annualleaveservices;

import com.ykb.annualleave.annualleaveservices.service.EmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeService employeeService;

    @Test
    void verifyDeservedAnnualLeaveDay()
    {
        Double deservedAnnualLeaveDay = employeeService.deservedAnnualLeaveDay(1l);
        assertThat(deservedAnnualLeaveDay).isEqualTo(0);
    }
}
