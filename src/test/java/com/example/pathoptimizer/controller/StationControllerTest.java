package com.example.pathoptimizer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(StationController.class)
public class StationControllerTest {
     @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPutStation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/stations/10")
                .contentType("application/json")
                .content("{\"name\": \"Barcelona\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
