package com.credit.module.loan.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.credit.module.loan.service.dataaccess"})
@EntityScan(basePackages = {"com.credit.module.loan.service.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.credit.module")
public class LoanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }
}
