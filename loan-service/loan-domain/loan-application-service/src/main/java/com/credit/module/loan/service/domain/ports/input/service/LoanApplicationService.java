package com.credit.module.loan.service.domain.ports.input.service;

import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.dto.list.LoanQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanQueryResponse;
import jakarta.validation.Valid;

public interface LoanApplicationService {

    CreateLoanResponse createLoan(@Valid CreateLoanCommand createLoanCommand);

    LoanQueryResponse getLoans(@Valid LoanQueryRequest loanQueryRequest);
}
