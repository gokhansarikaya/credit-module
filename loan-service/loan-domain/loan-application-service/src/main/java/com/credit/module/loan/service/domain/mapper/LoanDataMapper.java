package com.credit.module.loan.service.domain.mapper;

import com.credit.module.loan.service.domain.dto.create.CreateLoanRequest;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryResponse;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentResponse;
import com.credit.module.loan.service.domain.dto.list.LoanQueryResponse;
import com.credit.module.loan.service.domain.dto.list.LoanResponse;
import com.credit.module.loan.service.domain.dto.pay.PayLoanResponse;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.LoanInstallment;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.entity.PayInformation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanDataMapper {

    public Loan createLoanCommandToLoan(CreateLoanRequest createLoanRequest) {
        return Loan.builder()/*.customerId(new CustomerId(createLoanCommand.getCustomerId()))*/
                .interestRate(createLoanRequest.getInterestRate())
                .numberOfInstallment(createLoanRequest.getNumberOfInstalments())
                .loanAmount(new Money(createLoanRequest.getAmount()))
                .build();
    }

    public CreateLoanResponse loanToCreateLoanResponse(Loan loan) {
        return CreateLoanResponse.builder()
                .message("Loan Created Successfully")
                .build();
    }

    public LoanQueryResponse loanToLoanQueryResponse(List<Loan> loans) {
        return LoanQueryResponse.builder()
                .loanResponses(loans.stream().map(loan -> LoanResponse.builder()
                        .id(loan.getId().getValue())
                        .numberOfInstallments(loan.getNumberOfInstallment())
                        .isPaid(loan.isPaid())
                        .loanAmount(loan.getLoanAmount().getAmount())
                        .createDate(loan.getCreateDate())
                        .build()).collect(Collectors.toList())).build();
    }

    public LoanInstallmentQueryResponse loanInstallmentToLoanInstallmentQueryResponse(List<LoanInstallment> loanInstallments) {
        return LoanInstallmentQueryResponse.builder()
                .loanInstallments(loanInstallments.stream().map(loanInstallment -> LoanInstallmentResponse.builder()
                        .id(loanInstallment.getId().getValue())
                        .amount(loanInstallment.getAmount().getAmount())
                        .ispaid(loanInstallment.isPaid())
                        .paidAmount(loanInstallment.getPaidAmount().getAmount())
                        .paymentDate(loanInstallment.getPaymentDate())
                        .dueDate(loanInstallment.getDueDate())
                        .build()).collect(Collectors.toList())).build();
    }

    public PayLoanResponse payInformationToPayLoanResponse(PayInformation payInformation) {
        return PayLoanResponse.builder()
                .totalAmountSpent(payInformation.getTotalPaidAmount().getAmount())
                .isPaidCompletely(payInformation.getPaidCompletely())
                .paidInstallments(payInformation.getNumberOfPaidInstallments())
                .build();
    }
}
