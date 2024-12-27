package com.credit.module.loan.service.domain.entity;

import com.credit.module.loan.service.domain.LoanDomainException;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import com.credit.module.loan.service.domain.valueobject.LoanId;

import java.util.List;
import java.util.UUID;

import static com.credit.module.loan.service.domain.constants.DomainConstants.MAX_INTEREST_RATE;
import static com.credit.module.loan.service.domain.constants.DomainConstants.MIN_INTEREST_RATE;

public class Loan extends AggregateRoot<LoanId> {

    private Customer customer;
    private final Money loanAmount;
    private final Integer numberOfInstallment;
    private final double interestRate;
    public List<LoanInstallment> loanInstallments;

    private Loan(Builder builder) {
        super.setId(builder.loanId);
        customer = builder.customer;
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
//        validateCustomerLimit();
        validateInstallment();
        validateInterestRate();
    }

    private void validateInterestRate() {
        if (Double.compare(this.interestRate, MIN_INTEREST_RATE) < 0 || Double.compare(this.interestRate, MAX_INTEREST_RATE) > 0) {
            throw new LoanDomainException("Interest rate is not in valid range!");
        }
    }

    private void validateCustomerLimit() {
        if (this.loanAmount.isGreaterThan(this.customer.getCreditLimit())) {
            throw new LoanDomainException("Customer does not have enough credit limit!");
        }

    }

    private void validateInstallment() {
//        todo: fill later
    }


    public Customer getCustomer() {
        return customer;
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

    public void inizializeCustomer(Customer customer) {
        this.customer = customer;
    }


    public static final class Builder {
        private LoanId loanId;
        private Customer customer;
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

        public Builder customer(Customer val) {
            customer = val;
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
