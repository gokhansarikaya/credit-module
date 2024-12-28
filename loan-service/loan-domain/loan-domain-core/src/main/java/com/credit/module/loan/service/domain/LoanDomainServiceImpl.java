package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;

public class LoanDomainServiceImpl implements LoanDomainService {
    @Override
    public Loan validateAndInitiateLoan(Loan loan, Customer customer) {
        loan.inizializeCustomer(customer);
        loan.validateLoan();
        loan.initializeLoan();
        return loan;
//        return new LoanCreatedEvent(loan, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
