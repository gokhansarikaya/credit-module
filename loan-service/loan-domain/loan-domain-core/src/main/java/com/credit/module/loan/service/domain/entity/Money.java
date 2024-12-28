package com.credit.module.loan.service.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    public boolean isEqualOrGreaterThan(Money money) {
        return this.amount != null && this.amount.compareTo(money.getAmount()) >= 0;
    }

    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.amount)));
    }

    public Money multiply(Double multiplier) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
    }

    public Money devide(Integer divider) {
        return new Money(setScale(this.amount.divide(new BigDecimal(divider), 2, RoundingMode.HALF_EVEN)));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    private BigDecimal setScale(BigDecimal input) {
        return input;
//        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}
