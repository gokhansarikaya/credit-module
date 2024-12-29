package com.credit.module.loan.service.domain.entity;

import com.credit.module.loan.service.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    private final String name;
    private final String surname;
    private Money creditLimit;
    private Money usedCreditLimit;

/*    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }*/

    private Customer(Builder builder) {
        super.setId(builder.customerId);
        name = builder.name;
        surname = builder.surname;
        creditLimit = builder.creditLimit;
        usedCreditLimit = builder.usedCreditLimit;
    }

    public static Builder builder() {
        return new Builder();
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

    public void useCredit(Money usedCredit) {
        usedCreditLimit = usedCreditLimit.add(usedCredit);
    }

    public void finishCredit(Money usedCredit) {
        usedCreditLimit = usedCreditLimit.subtract(usedCredit);
    }


    public static final class Builder {
        private CustomerId customerId;
        private String name;
        private String surname;
        private Money creditLimit;
        private Money usedCreditLimit;

        private Builder() {
        }

        public Builder id(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }

        public Builder creditLimit(Money val) {
            creditLimit = val;
            return this;
        }

        public Builder usedCreditLimit(Money val) {
            usedCreditLimit = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
