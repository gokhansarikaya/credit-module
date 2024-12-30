package com.credit.module.loan.service.domain.ports.input.service;

import com.credit.module.loan.service.domain.dto.create.CreateLoanRequest;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryResponse;
import com.credit.module.loan.service.domain.dto.list.LoanQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanQueryResponse;
import com.credit.module.loan.service.domain.dto.pay.PayLoanRequest;
import com.credit.module.loan.service.domain.dto.pay.PayLoanResponse;
import jakarta.validation.Valid;

public interface LoanApplicationService {

    CreateLoanResponse createLoan(@Valid CreateLoanRequest createLoanRequest);

    LoanQueryResponse getLoans(@Valid LoanQueryRequest loanQueryRequest);

    LoanInstallmentQueryResponse getLoanInstallments(@Valid LoanInstallmentQueryRequest loanInstallmentQueryRequest);

    PayLoanResponse payLoan(@Valid PayLoanRequest payLoanRequest);
}
