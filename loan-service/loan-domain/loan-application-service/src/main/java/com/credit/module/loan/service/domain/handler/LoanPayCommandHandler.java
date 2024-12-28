package com.credit.module.loan.service.domain.handler;

import com.credit.module.loan.service.domain.LoanDomainException;
import com.credit.module.loan.service.domain.dto.pay.PayLoanRequest;
import com.credit.module.loan.service.domain.dto.pay.PayLoanResponse;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.entity.PayInformation;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import com.credit.module.loan.service.domain.valueobject.LoanId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class LoanPayCommandHandler {
    private final LoanRepository loanRepository;
    private final LoanDataMapper loanDataMapper;

    public LoanPayCommandHandler(LoanRepository loanRepository, LoanDataMapper loanDataMapper) {
        this.loanRepository = loanRepository;
        this.loanDataMapper = loanDataMapper;
    }

    @Transactional
    public PayLoanResponse payLoan(PayLoanRequest payLoanRequest) {
        Loan loan= checkLoan(payLoanRequest.getLoanId());
        PayInformation payInformation = loan.payLoan(new Money(payLoanRequest.getAmount()));
        loanRepository.save(loan);
        return loanDataMapper.payInformationToPayLoanResponse(payInformation);
    }

    private Loan checkLoan(UUID loanId) {
        Optional<Loan> loan = loanRepository.findById(new LoanId(loanId));
        if (loan.isEmpty()) {
            throw new LoanDomainException("Could not find loan with loan id: " + loanId);
        }
        return loan.get();
    }
}
