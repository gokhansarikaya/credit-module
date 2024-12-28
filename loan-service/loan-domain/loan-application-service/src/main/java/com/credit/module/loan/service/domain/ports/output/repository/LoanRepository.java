package com.credit.module.loan.service.domain.ports.output.repository;

import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import com.credit.module.loan.service.domain.valueobject.LoanId;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {

    Loan save(Loan loan);

    Optional<List<Loan>> findByCustomerId(CustomerId customerId);

    Optional<Loan> findById(LoanId loanId);
}
