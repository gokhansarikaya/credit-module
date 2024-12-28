package com.credit.module.loan.service.dataaccess.loan.adapter;

import com.credit.module.loan.service.dataaccess.loan.entity.LoanEntity;
import com.credit.module.loan.service.dataaccess.loan.mapper.LoanDataAccessMapper;
import com.credit.module.loan.service.dataaccess.loan.repository.LoanJpaRepository;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Optional<List<Loan>> findByCustomerId(CustomerId customerId) {
        Optional<List<LoanEntity>> loanEntities = loanJpaRepository.findByCustomerId(customerId.getValue());
        return loanEntities.map(
                loanEntityList -> loanEntityList.stream().map(
                        loanDataAccessMapper::loanEntityToLoan).collect(Collectors.toList()));
    }
}
