package com.credit.module.loan.service.domain.ports.output.repository;

import com.credit.module.loan.service.domain.entity.LoanInstallment;
import com.credit.module.loan.service.domain.valueobject.LoanId;

import java.util.List;
import java.util.Optional;

public interface LoanInstallmentRepository {
    Optional<List<LoanInstallment>> findByLoanId(LoanId loanId);
}
