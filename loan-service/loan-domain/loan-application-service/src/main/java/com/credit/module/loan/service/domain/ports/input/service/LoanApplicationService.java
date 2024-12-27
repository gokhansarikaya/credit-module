package com.credit.module.loan.service.domain.ports.input.service;

import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import jakarta.validation.Valid;

public interface LoanApplicationService {

    CreateLoanResponse createLoan(@Valid CreateLoanCommand createLoanCommand);
}
