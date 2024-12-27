package com.credit.module.loan.service.domain.entity;

import com.credit.module.loan.service.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    private Money creditLimit;
    private Money usedCreditLimit;

    public Customer(CustomerId customerId, Money creditLimit, Money usedCreditLimit) {
        super.setId(customerId);
        this.creditLimit = creditLimit;
        this.usedCreditLimit = usedCreditLimit;
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public Money getUsedCreditLimit() {
        return usedCreditLimit;
    }
}
