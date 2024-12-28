package com.credit.module.loan.service.domain.entity;

import com.credit.module.loan.service.domain.valueobject.LoanId;
import com.credit.module.loan.service.domain.valueobject.LoanInstallmentId;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.credit.module.loan.service.domain.constants.DomainConstants.UTC;

public class LoanInstallment extends BaseEntity<LoanInstallmentId> {
    private LoanId loanId;
    private Money amount;
    private Money paidAmount;
    private ZonedDateTime dueDate;
    private ZonedDateTime paymentDate;
    private boolean isPaid;

    private LoanInstallment(Builder builder) {
        super.setId(builder.loanInstallmentId);
        loanId = builder.loanId;
        amount = builder.amount;
        paidAmount = builder.paidAmount;
        dueDate = builder.dueDate;
        paymentDate = builder.paymentDate;
        isPaid = builder.isPaid;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void payInstallment(){
        paidAmount = amount;
        paymentDate = ZonedDateTime.now(ZoneId.of(UTC));
        isPaid = Boolean.TRUE;
    }

    public LoanId getLoanId() {
        return loanId;
    }

    public Money getAmount() {
        return amount;
    }

    public Money getPaidAmount() {
        return paidAmount;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public ZonedDateTime getPaymentDate() {
        return paymentDate;
    }

    public boolean isPaid() {
        return isPaid;
    }


    public static final class Builder {
        private LoanInstallmentId loanInstallmentId;
        private LoanId loanId;
        private Money amount;
        private Money paidAmount;
        private ZonedDateTime dueDate;
        private ZonedDateTime paymentDate;
        private boolean isPaid;

        private Builder() {
        }

        public Builder loanInstallmentId(LoanInstallmentId val) {
            loanInstallmentId = val;
            return this;
        }

        public Builder loanId(LoanId val) {
            loanId = val;
            return this;
        }

        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        public Builder paidAmount(Money val) {
            paidAmount = val;
            return this;
        }

        public Builder dueDate(ZonedDateTime val) {
            dueDate = val;
            return this;
        }

        public Builder paymentDate(ZonedDateTime val) {
            paymentDate = val;
            return this;
        }

        public Builder isPaid(boolean val) {
            isPaid = val;
            return this;
        }

        public LoanInstallment build() {
            return new LoanInstallment(this);
        }
    }
}
