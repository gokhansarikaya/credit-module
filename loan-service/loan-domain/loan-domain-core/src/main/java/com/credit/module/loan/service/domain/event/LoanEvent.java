package com.credit.module.loan.service.domain.event;

import com.credit.module.loan.service.domain.entity.Loan;

import java.time.ZonedDateTime;

public abstract class LoanEvent {
    private final Loan loan;
    private final ZonedDateTime createdAt;

    public LoanEvent(Loan loan, ZonedDateTime createdAt) {
        this.loan = loan;
        this.createdAt = createdAt;
    }

    public Loan getLoan() {
        return loan;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
