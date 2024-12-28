package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;

public interface LoanDomainService {
    Loan validateAndInitiateLoan(Loan loan, Customer customer);
}
