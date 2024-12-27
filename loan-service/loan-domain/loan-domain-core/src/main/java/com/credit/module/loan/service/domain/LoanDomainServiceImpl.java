package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.event.LoanCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LoanDomainServiceImpl implements LoanDomainService {
    @Override
    public Loan validateAndInitiateLoan(Loan loan, Customer customer) {
        loan.validateLoan();
        loan.validateLoan();
        return loan;
//        return new LoanCreatedEvent(loan, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
