package com.credit.module.loan.service.domain.handler;

import com.credit.module.loan.service.domain.LoanDomainException;
import com.credit.module.loan.service.domain.LoanDomainService;
import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.event.LoanCreatedEvent;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.output.repository.CustomerRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class LoanCreateCommandHandler {

    private final LoanDomainService loanDomainService;
    private final LoanDataMapper loanDataMapper;
    private final CustomerRepository customerRepository;

    public LoanCreateCommandHandler(LoanDomainService loanDomainService, LoanDataMapper loanDataMapper, CustomerRepository customerRepository) {
        this.loanDomainService = loanDomainService;
        this.loanDataMapper = loanDataMapper;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CreateLoanResponse createLoan(CreateLoanCommand createLoanCommand) {
        Customer customer = checkCustomer(createLoanCommand.getCustomerId());
        Loan loan = loanDataMapper.createLoanCommandToLoan(createLoanCommand);
        Loan createdLoan = loanDomainService.validateAndInitiateLoan(loan, customer);
        CreateLoanResponse createLoanResponse = loanDataMapper.loanToCreateLoanResponse(createdLoan);
        return createLoanResponse;
    }

    private Customer checkCustomer(UUID customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomer(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new LoanDomainException("Could not find customer with customer id: " + customerId);
        }
        return optionalCustomer.get();
    }


}
