package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryResponse;
import com.credit.module.loan.service.domain.dto.list.LoanQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanQueryResponse;
import com.credit.module.loan.service.domain.handler.LoanCreateCommandHandler;
import com.credit.module.loan.service.domain.handler.LoanInstallmentQueryHandler;
import com.credit.module.loan.service.domain.handler.LoanListCommandHandler;
import com.credit.module.loan.service.domain.ports.input.service.LoanApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class LoanApplicationServiceIml implements LoanApplicationService {
    private final LoanCreateCommandHandler loanCreateCommandHandler;
    private final LoanListCommandHandler loanListCommandHandler;
    private final LoanInstallmentQueryHandler loanInstallmentQueryHandler;

    public LoanApplicationServiceIml(LoanCreateCommandHandler loanCreateCommandHandler,
                                     LoanListCommandHandler loanListCommandHandler,
                                     LoanInstallmentQueryHandler loanInstallmentQueryHandler) {
        this.loanCreateCommandHandler = loanCreateCommandHandler;
        this.loanListCommandHandler = loanListCommandHandler;
        this.loanInstallmentQueryHandler = loanInstallmentQueryHandler;
    }

    @Override
    public CreateLoanResponse createLoan(CreateLoanCommand createLoanCommand) {
        return loanCreateCommandHandler.createLoan(createLoanCommand);
    }

    @Override
    public LoanQueryResponse getLoans(LoanQueryRequest loanQueryRequest) {
        return loanListCommandHandler.getLoans(loanQueryRequest);
    }

    @Override
    public LoanInstallmentQueryResponse getLoanInstallments(LoanInstallmentQueryRequest loanInstallmentQueryRequest) {
        return loanInstallmentQueryHandler.getLoanInstallments(loanInstallmentQueryRequest);
    }
}
