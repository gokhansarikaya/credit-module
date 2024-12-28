package com.credit.module.loan.service.domain.event;

import com.credit.module.loan.service.domain.entity.Loan;

import java.time.ZonedDateTime;

public class LoanCreatedEvent extends LoanEvent {

    public LoanCreatedEvent(Loan loan, ZonedDateTime zonedDateTime) {
        super(loan, zonedDateTime);
    }
}
