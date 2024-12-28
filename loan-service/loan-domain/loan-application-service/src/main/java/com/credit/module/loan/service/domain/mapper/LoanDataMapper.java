package com.credit.module.loan.service.domain.mapper;

import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.dto.list.LoanQueryResponse;
import com.credit.module.loan.service.domain.dto.list.LoanResponse;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.Money;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanDataMapper {

    public Loan createLoanCommandToLoan(CreateLoanCommand createLoanCommand) {
        return Loan.builder()/*.customerId(new CustomerId(createLoanCommand.getCustomerId()))*/
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

    public LoanQueryResponse loanToListLoanResponse(List<Loan> loans) {
        return LoanQueryResponse.builder()
                .loanResponses(loans.stream().map(loan -> LoanResponse.builder()
                        .id(loan.getId().getValue())
                        .numberOfInstallments(loan.getNumberOfInstallment())
                        .isPaid(loan.isPaid())
                        .loanAmount(loan.getLoanAmount().getAmount())
                        .createDate(loan.getCreateDate())
                        .build()).collect(Collectors.toList())).build();
    }
}
