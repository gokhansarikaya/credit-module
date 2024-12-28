package com.credit.module.loan.service.domain.handler;

import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryResponse;
import com.credit.module.loan.service.domain.entity.LoanInstallment;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.output.repository.LoanInstallmentRepository;
import com.credit.module.loan.service.domain.valueobject.LoanId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class LoanInstallmentQueryHandler {
    private final LoanInstallmentRepository loanInstallmentRepository;
    private final LoanDataMapper loanDataMapper;

    public LoanInstallmentQueryHandler(LoanInstallmentRepository loanInstallmentRepository, LoanDataMapper loanDataMapper) {
        this.loanInstallmentRepository = loanInstallmentRepository;
        this.loanDataMapper = loanDataMapper;
    }

    @Transactional(readOnly = true)
    public LoanInstallmentQueryResponse getLoanInstallments(LoanInstallmentQueryRequest loanInstallmentQueryRequest) {
        Optional<List<LoanInstallment>> loanInstallments = loanInstallmentRepository.findByLoanId(new LoanId(loanInstallmentQueryRequest.getLoanId()));

        return loanDataMapper.loanInstallmentToLoanInstallmentQueryResponse(
                loanInstallments.orElseGet(ArrayList::new));
    }
}
