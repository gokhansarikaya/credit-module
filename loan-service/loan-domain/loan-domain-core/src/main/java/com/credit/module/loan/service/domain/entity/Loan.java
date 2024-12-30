package com.credit.module.loan.service.domain.entity;

import com.credit.module.loan.service.domain.exception.LoanDomainException;
import com.credit.module.loan.service.domain.utility.DateUtility;
import com.credit.module.loan.service.domain.valueobject.LoanId;
import com.credit.module.loan.service.domain.valueobject.LoanInstallmentId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static com.credit.module.loan.service.domain.constants.DomainConstants.*;
import static com.credit.module.loan.service.domain.utility.DateUtility.*;

public class Loan extends AggregateRoot<LoanId> {

    private Customer customer;
    private final Money loanAmount;
    private final Integer numberOfInstallment;
    private final Double interestRate;
    private ZonedDateTime createDate;
    public List<LoanInstallment> loanInstallments;

    public Boolean isPaid() {
        for (LoanInstallment loanInstallment : loanInstallments) {
            if (!loanInstallment.isPaid())
                return false;
        }
        return true;
    }


    public void initializeLoan() {
        setId(new LoanId(UUID.randomUUID()));
        this.createDate = ZonedDateTime.now(ZoneId.of(UTC));
        initializeLoanInstallments();
        this.customer.useCredit(loanAmount);
    }

    public void validateLoan() {
        validateCustomerLimit();
        validateInstallment();
        validateInterestRate();
        validateLoanAmount();
    }

    private void initializeLoanInstallments() {
        this.loanInstallments = new ArrayList<>();
        for (int i = 0; i < this.numberOfInstallment; i++) {
            this.loanInstallments.add(
                    LoanInstallment.builder()
                            .loanId(super.getId())
                            .loanInstallmentId(new LoanInstallmentId((long) (i + 1)))
                            .amount((this.loanAmount.multiply(this.interestRate + 1)).devide(this.numberOfInstallment))
                            .dueDate(calculateDueDate(i))
                            .paidAmount(Money.ZERO)
                            .isPaid(Boolean.FALSE)
                            .build());
        }
    }

    private ZonedDateTime calculateDueDate(int i) {
        return endOfTheDay(firstDayOfMonth(DateUtility.addMonth(this.createDate, i + 1)));
    }

    private void validateCustomerLimit() {
        if (this.loanAmount.isGreaterThan(this.customer.getCreditLimit().subtract(this.customer.getUsedCreditLimit()))) {
            throw new LoanDomainException("Customer does not have enough credit limit!");
        }
    }

    private void validateInstallment() {
        if (!INSTALLMENT_VALUES.contains(numberOfInstallment)) {
            throw new LoanDomainException("Installment value is not valid!");
        }
    }

    private void validateInterestRate() {
        if (Double.compare(this.interestRate, MIN_INTEREST_RATE) < 0 || Double.compare(this.interestRate, MAX_INTEREST_RATE) > 0) {
            throw new LoanDomainException("Interest rate is not in valid range!");
        }
    }

    private void validateLoanAmount() {
        if (!loanAmount.isGreaterThanZero()) {
            throw new LoanDomainException("Loan amount : " + loanAmount.getAmount() + " is not valid!");
        }

    }

    public PayInformation payLoan(Money payAmount) {
        ZonedDateTime lastetPayableDate = lastPayableDate(ZonedDateTime.now(ZoneId.of(UTC)), PAYABLE_MONTH_RANGE);
        Money totalPayAmount = payAmount;
        int payCount = 0;
        for (LoanInstallment loanInstallment : loanInstallments.stream().sorted(Comparator.comparing(LoanInstallment::getDueDate)).toList()) {
            if (!loanInstallment.isPaid()
                    && totalPayAmount.isEqualOrGreaterThan(loanInstallment.getAmount())
                    && loanInstallment.getDueDate().compareTo(lastetPayableDate) <= 0) {
                loanInstallment.payInstallment();
                payCount++;
                totalPayAmount = totalPayAmount.subtract(loanInstallment.getAmount());
            }
        }
        customerUsedLimitProcess();

        return PayInformation.builder().
                totalPaidAmount(payAmount.subtract(totalPayAmount)).
                numberOfPaidInstallments(payCount)
                .isPaidCompletely(isPaid()).build();
    }

    private ZonedDateTime lastPayableDate(ZonedDateTime now, Integer payableMonthRange) {
        return endOfTheDay(addMonth(firstDayOfMonth(now), payableMonthRange -1));
    }

    private void customerUsedLimitProcess() {
        if (isPaid())
            this.customer.finishCredit(loanAmount);
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

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public List<LoanInstallment> getLoanInstallments() {
        return loanInstallments;
    }

    public void setLoanInstallments(List<LoanInstallment> loanInstallments) {
        this.loanInstallments = loanInstallments;
    }

    public void inizializeCustomer(Customer customer) {
        this.customer = customer;
    }

    private Loan(Builder builder) {
        super.setId(builder.loanId);
        customer = builder.customer;
        loanAmount = builder.loanAmount;
        numberOfInstallment = builder.numberOfInstallment;
        interestRate = builder.interestRate;
        createDate = builder.createDate;
        setLoanInstallments(builder.loanInstallments);
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private LoanId loanId;
        private Customer customer;
        private Money loanAmount;
        private Integer numberOfInstallment;
        private double interestRate;
        private ZonedDateTime createDate;
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

        public Builder createDate(ZonedDateTime val) {
            createDate = val;
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
