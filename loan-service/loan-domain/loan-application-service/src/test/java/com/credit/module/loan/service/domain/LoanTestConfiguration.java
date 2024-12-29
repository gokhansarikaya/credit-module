package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.ports.output.repository.CustomerRepository;
import com.credit.module.loan.service.domain.ports.output.repository.LoanInstallmentRepository;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.credit.module")
public class LoanTestConfiguration {

    @Bean
    public LoanRepository loanRepository() {
        return Mockito.mock(LoanRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public LoanInstallmentRepository loanInstallmentRepository() {
        return Mockito.mock(LoanInstallmentRepository.class);
    }

    @Bean
    public LoanDomainService loanDomainService() {
        return new LoanDomainServiceImpl();
    }

}
