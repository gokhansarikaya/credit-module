package com.credit.module.loan.service.domain.handler;

import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryResponse;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.helper.LoanHelper;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class LoanInstallmentQueryHandler {
    private final LoanDataMapper loanDataMapper;
    private final LoanHelper loanHelper;

    public LoanInstallmentQueryHandler(LoanRepository loanRepository,
                                       LoanDataMapper loanDataMapper, LoanHelper loanHelper) {
        this.loanDataMapper = loanDataMapper;
        this.loanHelper = loanHelper;
    }

    @Transactional(readOnly = true)
    public LoanInstallmentQueryResponse getLoanInstallments(LoanInstallmentQueryRequest loanInstallmentQueryRequest) {
        Loan loan = loanHelper.checkLoan(loanInstallmentQueryRequest.getLoanId());
        return loanDataMapper.loanInstallmentToLoanInstallmentQueryResponse(loan.getLoanInstallments());
    }
}
