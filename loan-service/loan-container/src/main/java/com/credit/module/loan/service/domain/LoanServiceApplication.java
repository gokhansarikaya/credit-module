package com.credit.module.loan.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = {"com.credit.module.loan.service.dataaccess"})
//@EntityScan(basePackages = {........})
@SpringBootApplication(scanBasePackages = "com.credit.module")
public class LoanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }
}
