package com.credit.module.loan.service.domain.handler;

import com.credit.module.loan.service.domain.LoanDomainService;
import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.helper.LoanHelper;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class LoanCreateCommandHandler {

    private final LoanDomainService loanDomainService;
    private final LoanDataMapper loanDataMapper;
    private final LoanRepository loanRepository;
    private final LoanHelper loanHelper;

    public LoanCreateCommandHandler(LoanDomainService loanDomainService,
                                    LoanDataMapper loanDataMapper,
                                    LoanRepository loanRepository,
                                    LoanHelper loanHelper) {
        this.loanDomainService = loanDomainService;
        this.loanDataMapper = loanDataMapper;
        this.loanRepository = loanRepository;
        this.loanHelper = loanHelper;
    }

    @Transactional
    public CreateLoanResponse createLoan(CreateLoanCommand createLoanCommand) {
        Customer customer = loanHelper.checkCustomer(createLoanCommand.getCustomerId());
        Loan loan = loanDataMapper.createLoanCommandToLoan(createLoanCommand);
        Loan createdLoan = loanDomainService.validateAndInitiateLoan(loan, customer);
        loanRepository.save(createdLoan);
        CreateLoanResponse createLoanResponse = loanDataMapper.loanToCreateLoanResponse(createdLoan);
        return createLoanResponse;
    }
}
