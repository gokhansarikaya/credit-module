package com.credit.module.loan.service.dataaccess.loan.adapter;

import com.credit.module.loan.service.dataaccess.loan.entity.LoanInstallmentEntity;
import com.credit.module.loan.service.dataaccess.loan.mapper.LoanDataAccessMapper;
import com.credit.module.loan.service.dataaccess.loan.repository.LoanInstallmentJpaRepository;
import com.credit.module.loan.service.domain.entity.LoanInstallment;
import com.credit.module.loan.service.domain.ports.output.repository.LoanInstallmentRepository;
import com.credit.module.loan.service.domain.valueobject.LoanId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LoanInstallmentRepositoryImpl implements LoanInstallmentRepository {
    private final LoanDataAccessMapper loanDataAccessMapper;
    private final LoanInstallmentJpaRepository loanInstallmentJpaRepository;

    public LoanInstallmentRepositoryImpl(LoanDataAccessMapper loanDataAccessMapper, LoanInstallmentJpaRepository loanInstallmentJpaRepository) {
        this.loanDataAccessMapper = loanDataAccessMapper;
        this.loanInstallmentJpaRepository = loanInstallmentJpaRepository;
    }

    @Override
    public Optional<List<LoanInstallment>> findByLoanId(LoanId loanId) {
        Optional<List<LoanInstallmentEntity>> loanInstallmentEntities = loanInstallmentJpaRepository.findByLoanIdOrderByIdAsc(loanId.getValue());

        return loanInstallmentEntities.map(
                loanInstallmentEntityist -> loanInstallmentEntityist.stream().map(
                        loanDataAccessMapper::loanInstallmentEntityToLoanInstallment).collect(Collectors.toList()));
    }
}
