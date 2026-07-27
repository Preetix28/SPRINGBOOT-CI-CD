package com.preeti.springboot_ci_cd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AppControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AppController()).build();
    }

    @Test
    void testHomeEndpoint() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Spring Boot CI/CD Demo Application")))
                .andExpect(content().string(
                        containsString("Welcome to the application!")));
    }

    @Test
    void testHealthEndpoint() throws Exception {

        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Application is UP"));
    }

    @Test
    void testInfoEndpoint() throws Exception {

        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Application : Spring Boot CI/CD Demo")))
                .andExpect(content().string(
                        containsString("Version")))
                .andExpect(content().string(
                        containsString("Hostname")))
                .andExpect(content().string(
                        containsString("Time")));
    }
}