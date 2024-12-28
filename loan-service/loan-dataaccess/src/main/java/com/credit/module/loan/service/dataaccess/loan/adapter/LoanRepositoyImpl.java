package com.credit.module.loan.service.dataaccess.loan.adapter;

import com.credit.module.loan.service.dataaccess.loan.mapper.LoanDataAccessMapper;
import com.credit.module.loan.service.dataaccess.loan.repository.LoanJpaRepository;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import org.springframework.stereotype.Component;

@Component
public class LoanRepositoyImpl implements LoanRepository {

    private final LoanDataAccessMapper loanDataAccessMapper;
    private final LoanJpaRepository loanJpaRepository;

    public LoanRepositoyImpl(LoanDataAccessMapper loanDataAccessMapper, LoanJpaRepository loanJpaRepository) {
        this.loanDataAccessMapper = loanDataAccessMapper;
        this.loanJpaRepository = loanJpaRepository;
    }

    @Override
    public Loan save(Loan loan) {
        loanJpaRepository.save(loanDataAccessMapper.loanToLoanEntity(loan));
        return null;
    }
}
