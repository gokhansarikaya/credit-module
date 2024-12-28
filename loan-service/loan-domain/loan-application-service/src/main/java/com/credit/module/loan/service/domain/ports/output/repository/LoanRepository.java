package com.credit.module.loan.service.domain.ports.output.repository;

import com.credit.module.loan.service.domain.entity.Loan;

public interface LoanRepository {

    Loan save(Loan loan);
}
