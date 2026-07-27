package com.preeti.springboot_ci_cd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.LocalDateTime;

@RestController
public class AppController {

    @GetMapping("/")
    public String home() throws Exception {
        return """
                =====================================
                Spring Boot CI/CD Demo Application
                =====================================
                Welcome to the application!
                """;
    }

    @GetMapping("/health")
    public String health() {
        return "Application is UP";
    }

    @GetMapping("/info")
    public String info() throws Exception {

        return """
                Application : Spring Boot CI/CD Demo
                Version     : 1.0.0
                Hostname    : %s
                Time        : %s
                """
                .formatted(
                        InetAddress.getLocalHost().getHostName(),
                        LocalDateTime.now());
    }
}