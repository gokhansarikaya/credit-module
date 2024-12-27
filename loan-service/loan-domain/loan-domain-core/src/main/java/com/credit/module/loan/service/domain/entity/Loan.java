package com.credit.module.loan.service.domain.entity;

import com.credit.module.loan.service.domain.valueobject.CustomerId;
import com.credit.module.loan.service.domain.valueobject.LoanId;

import java.util.List;
import java.util.UUID;

public class Loan extends AggregateRoot<LoanId> {

    private final CustomerId customerId;
    private final Money loanAmount;
    private final Integer numberOfInstallment;
    private final double interestRate;
    public List<LoanInstallment> loanInstallments;

    private Loan(Builder builder) {
        super.setId(builder.loanId);
        customerId = builder.customerId;
        loanAmount = builder.loanAmount;
        numberOfInstallment = builder.numberOfInstallment;
        interestRate = builder.interestRate;
        setLoanInstallments(builder.loanInstallments);
    }

    public static Builder builder() {
        return new Builder();
    }


    public void initializeLoan() {
        setId(new LoanId(UUID.randomUUID()));
//        todo: fill later
    }

    public void validateLoan() {
//        todo: fill later
    }

    private void validateInstallment() {
//        todo: fill later
    }


    public CustomerId getCustomerId() {
        return customerId;
    }

    public Money getLoanAmount() {
        return loanAmount;
    }

    public Integer getNumberOfInstallment() {
        return numberOfInstallment;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setLoanInstallments(List<LoanInstallment> loanInstallments) {
        this.loanInstallments = loanInstallments;
    }


    public static final class Builder {
        private LoanId loanId;
        private CustomerId customerId;
        private Money loanAmount;
        private Integer numberOfInstallment;
        private double interestRate;
        private List<LoanInstallment> loanInstallments;

        private Builder() {
        }

        public Builder loanId(LoanId val) {
            loanId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder loanAmount(Money val) {
            loanAmount = val;
            return this;
        }

        public Builder numberOfInstallment(Integer val) {
            numberOfInstallment = val;
            return this;
        }

        public Builder interestRate(double val) {
            interestRate = val;
            return this;
        }

        public Builder loanInstallments(List<LoanInstallment> val) {
            loanInstallments = val;
            return this;
        }

        public Loan build() {
            return new Loan(this);
        }
    }
}
