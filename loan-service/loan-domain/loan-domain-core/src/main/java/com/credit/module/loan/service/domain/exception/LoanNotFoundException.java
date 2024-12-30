package com.credit.module.loan.service.domain.exception;

public class LoanNotFoundException extends DomainException {
    public LoanNotFoundException(String message) {
        super(message);
    }

    public LoanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}