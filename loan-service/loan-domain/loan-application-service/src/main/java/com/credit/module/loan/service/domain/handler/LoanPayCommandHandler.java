package com.credit.module.loan.service.domain.handler;

import com.credit.module.loan.service.domain.dto.pay.PayLoanRequest;
import com.credit.module.loan.service.domain.dto.pay.PayLoanResponse;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.entity.PayInformation;
import com.credit.module.loan.service.domain.helper.LoanHelper;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class LoanPayCommandHandler {
    private final LoanRepository loanRepository;
    private final LoanDataMapper loanDataMapper;
    private final LoanHelper loanHelper;

    public LoanPayCommandHandler(LoanRepository loanRepository, LoanDataMapper loanDataMapper, LoanHelper loanHelper) {
        this.loanRepository = loanRepository;
        this.loanDataMapper = loanDataMapper;
        this.loanHelper = loanHelper;
    }

    @Transactional
    public PayLoanResponse payLoan(PayLoanRequest payLoanRequest) {
        Loan loan = loanHelper.checkLoan(payLoanRequest.getLoanId());
        PayInformation payInformation = loan.payLoan(new Money(payLoanRequest.getAmount()));
        loanRepository.save(loan);
        return loanDataMapper.payInformationToPayLoanResponse(payInformation);
    }
}
