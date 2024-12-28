package com.credit.module.loan.service.dataaccess.loan.mapper;

import com.credit.module.loan.service.dataaccess.loan.entity.LoanEntity;
import com.credit.module.loan.service.dataaccess.loan.entity.LoanInstallmentEntity;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.LoanInstallment;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.valueobject.LoanId;
import com.credit.module.loan.service.domain.valueobject.LoanInstallmentId;
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

    public Loan loanEntityToLoan(LoanEntity loanEntity) {
        return Loan.builder()
                .loanId(new LoanId(loanEntity.getId()))
                .loanAmount(new Money(loanEntity.getLoanAmount()))
                .createDate(loanEntity.getCreateDate())
                .numberOfInstallment(loanEntity.getNumberOfInstallment())
                .loanInstallments(loanEntity.getLoanInstallments()
                        .stream()
                        .map(this::loanInstallmentEntityToLoanInstallment).collect(Collectors.toList()))
                .build();
    }

    public LoanInstallment loanInstallmentEntityToLoanInstallment(LoanInstallmentEntity loanInstallmentEntity) {
        return LoanInstallment.builder()
                .loanInstallmentId(new LoanInstallmentId(loanInstallmentEntity.getId()))
                .loanId(new LoanId(loanInstallmentEntity.getLoan().getId()))
                .dueDate(loanInstallmentEntity.getDueDate())
                .isPaid(loanInstallmentEntity.getIsPaid())
                .amount(new Money(loanInstallmentEntity.getAmount()))
                .paidAmount(new Money(loanInstallmentEntity.getPaidAmount()))
                .paymentDate(loanInstallmentEntity.getPaymentDate())
                .build();

    }


}
