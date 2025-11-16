package com.bank.beneficiaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BeneficiaireServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeneficiaireServiceApplication.class, args);
    }
}

