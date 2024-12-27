package com.credit.module.loan.service.domain.mapper;

import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class LoanDataMapper {

    public Loan createLoanCommandToLoan(CreateLoanCommand createLoanCommand) {
        return Loan.builder().customerId(new CustomerId(createLoanCommand.getCustomerId()))
                .interestRate(createLoanCommand.getInterestRate())
                .numberOfInstallment(createLoanCommand.getNumberOfInstalments())
                .loanAmount(new Money(createLoanCommand.getAmount()))
                .build();
    }

    public CreateLoanResponse loanToCreateLoanResponse(Loan loan) {
        return CreateLoanResponse.builder()
                .message("Loan Created Successfully")
                .build();
    }
}
