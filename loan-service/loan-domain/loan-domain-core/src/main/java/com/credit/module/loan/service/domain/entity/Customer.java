package com.credit.module.loan.service.domain.entity;

import com.credit.module.loan.service.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    private String name;
    private String surname;
    private Money creditLimit;
    private Money usedCreditLimit;

    public Customer(CustomerId customerId, String name, String surname, Money creditLimit, Money usedCreditLimit) {
        super.setId(customerId);
        this.name = name;
        this.surname = surname;
        this.creditLimit = creditLimit;
        this.usedCreditLimit = usedCreditLimit;
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public Money getUsedCreditLimit() {
        return usedCreditLimit;
    }
}
