package com.ykb.annualleave.annualleaveservices;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AnnualLeaveTests extends AnnualLeaveDtoServicesApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testEmployee() throws Exception {
        mockMvc.perform(post("/employees")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value("5")).andExpect(jsonPath("$.jobStartDate").value("2022-04-05\""))
                .andExpect(jsonPath("$.name").value("Gokhan")).andExpect(jsonPath("$.surname").value("Pehlivan"));
    }
}
