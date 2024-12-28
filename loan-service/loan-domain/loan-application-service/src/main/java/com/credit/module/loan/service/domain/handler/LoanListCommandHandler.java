package com.credit.module.loan.service.domain.handler;

import com.credit.module.loan.service.domain.dto.list.LoanQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanQueryResponse;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class LoanListCommandHandler {

    private final LoanRepository loanRepository;
    private final LoanDataMapper loanDataMapper;

    public LoanListCommandHandler(LoanRepository loanRepository, LoanDataMapper loanDataMapper) {
        this.loanRepository = loanRepository;
        this.loanDataMapper = loanDataMapper;
    }

    @Transactional(readOnly = true)
    public LoanQueryResponse getLoans(LoanQueryRequest loanQueryRequest) {
        Optional<List<Loan>> loans = loanRepository.findByCustomerId(new CustomerId(loanQueryRequest.getCustomerId()));

        LoanQueryResponse loanQueryResponse = loanDataMapper.loanToListLoanResponse(loans.orElseGet(ArrayList::new));
        return loanQueryResponse;
    }
}
