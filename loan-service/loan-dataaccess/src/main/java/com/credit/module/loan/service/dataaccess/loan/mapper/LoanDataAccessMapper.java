package com.credit.module.loan.service.dataaccess.loan.mapper;

import com.credit.module.loan.service.dataaccess.loan.entity.LoanEntity;
import com.credit.module.loan.service.dataaccess.loan.entity.LoanInstallmentEntity;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.LoanInstallment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanDataAccessMapper {

    public LoanEntity loanToLoanEntity(Loan loan) {
        LoanEntity loanEntity = LoanEntity.builder()
                .id(loan.getId().getValue())
                .customerId(loan.getCustomer().getId().getValue())
                .loanAmount(loan.getLoanAmount().getAmount())
                .numberOfInstallment(loan.getNumberOfInstallment())
                .createDate(loan.getCreateDate())
                .loanInstallments(loanInstallmentsToLoanInstallmentEntity(loan.getLoanInstallments()))
                .isPaid(loan.isPaid())
                .build();
        loanEntity.getLoanInstallments().forEach(loanInstallmentEntity -> loanInstallmentEntity.setLoan(loanEntity));
        return loanEntity;
    }

    private List<LoanInstallmentEntity> loanInstallmentsToLoanInstallmentEntity(List<LoanInstallment> loanInstallments) {
        List<LoanInstallmentEntity> loanInstallmentEntities = loanInstallments.stream()
                .map(loanInstallment -> LoanInstallmentEntity.builder()
                        .id(loanInstallment.getId().getValue())
                        .amount(loanInstallment.getAmount().getAmount())
                        .paidAmount(loanInstallment.getPaidAmount().getAmount())
                        .dueDate(loanInstallment.getDueDate())
                        .paymentDate(loanInstallment.getPaymentDate())
                        .isPaid(loanInstallment.isPaid())
                        .build())
                .collect(Collectors.toList());
        return loanInstallmentEntities;
    }
}
