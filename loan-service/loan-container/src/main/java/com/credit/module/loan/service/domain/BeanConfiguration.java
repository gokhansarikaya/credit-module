package com.credit.module.loan.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public LoanDomainService loanDomainService() {
        return new LoanDomainServiceImpl();
    }
}
