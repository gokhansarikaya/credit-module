package com.credit.module.loan.service.domain.handler;

import com.credit.module.loan.service.domain.LoanDomainService;
import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.event.LoanCreatedEvent;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class LoanCreateCommandHandler {

    private final LoanDomainService loanDomainService;
    private final LoanDataMapper loanDataMapper;

    public LoanCreateCommandHandler(LoanDomainService loanDomainService, LoanDataMapper loanDataMapper) {
        this.loanDomainService = loanDomainService;
        this.loanDataMapper = loanDataMapper;
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
        return null;
    }


}
