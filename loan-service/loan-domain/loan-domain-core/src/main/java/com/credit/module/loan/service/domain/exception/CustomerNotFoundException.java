package com.credit.module.loan.service.domain.exception;

public class CustomerNotFoundException extends DomainException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
