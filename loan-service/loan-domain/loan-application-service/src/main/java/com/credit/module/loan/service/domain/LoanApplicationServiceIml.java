package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.handler.LoanCreateCommandHandler;
import com.credit.module.loan.service.domain.ports.input.service.LoanApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class LoanApplicationServiceIml implements LoanApplicationService {
    private final LoanCreateCommandHandler loanCreateCommandHandler;

    public LoanApplicationServiceIml(LoanCreateCommandHandler loanCreateCommandHandler) {
        this.loanCreateCommandHandler = loanCreateCommandHandler;
    }

    @Override
    public CreateLoanResponse createLoan(CreateLoanCommand createLoanCommand) {
        return loanCreateCommandHandler.createLoan(createLoanCommand);
    }
}
