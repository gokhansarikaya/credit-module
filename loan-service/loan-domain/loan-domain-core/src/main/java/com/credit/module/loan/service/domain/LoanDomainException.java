package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.exception.DomainException;

public class LoanDomainException extends DomainException {
    public LoanDomainException(String message) {
        super(message);
    }

    public LoanDomainException(String message, Throwable cause){
        super(message, cause);
    }
}
