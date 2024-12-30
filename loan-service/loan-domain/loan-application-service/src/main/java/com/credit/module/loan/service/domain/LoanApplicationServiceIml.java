package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.dto.create.CreateLoanRequest;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryResponse;
import com.credit.module.loan.service.domain.dto.list.LoanQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanQueryResponse;
import com.credit.module.loan.service.domain.dto.pay.PayLoanRequest;
import com.credit.module.loan.service.domain.dto.pay.PayLoanResponse;
import com.credit.module.loan.service.domain.handler.LoanCreateCommandHandler;
import com.credit.module.loan.service.domain.handler.LoanInstallmentQueryHandler;
import com.credit.module.loan.service.domain.handler.LoanListCommandHandler;
import com.credit.module.loan.service.domain.handler.LoanPayCommandHandler;
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
    private final LoanPayCommandHandler loanPayCommandHandler;

    public LoanApplicationServiceIml(LoanCreateCommandHandler loanCreateCommandHandler,
                                     LoanListCommandHandler loanListCommandHandler,
                                     LoanInstallmentQueryHandler loanInstallmentQueryHandler, LoanPayCommandHandler loanPayCommandHandler) {
        this.loanCreateCommandHandler = loanCreateCommandHandler;
        this.loanListCommandHandler = loanListCommandHandler;
        this.loanInstallmentQueryHandler = loanInstallmentQueryHandler;
        this.loanPayCommandHandler = loanPayCommandHandler;
    }

    @Override
    public CreateLoanResponse createLoan(CreateLoanRequest createLoanRequest) {
        return loanCreateCommandHandler.createLoan(createLoanRequest);
    }

    @Override
    public LoanQueryResponse getLoans(LoanQueryRequest loanQueryRequest) {
        return loanListCommandHandler.getLoans(loanQueryRequest);
    }

    @Override
    public LoanInstallmentQueryResponse getLoanInstallments(LoanInstallmentQueryRequest loanInstallmentQueryRequest) {
        return loanInstallmentQueryHandler.getLoanInstallments(loanInstallmentQueryRequest);
    }

    @Override
    public PayLoanResponse payLoan(PayLoanRequest payLoanRequest) {
        return loanPayCommandHandler.payLoan(payLoanRequest);
    }
}
