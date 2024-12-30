package com.credit.module.loan.service.domain.exception;

public class LoanDomainException extends DomainException {
    public LoanDomainException(String message) {
        super(message);
    }

    public LoanDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
