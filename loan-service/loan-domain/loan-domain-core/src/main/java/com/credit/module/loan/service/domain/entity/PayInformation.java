package com.credit.module.loan.service.domain.entity;

public class PayInformation {
    private final Money totalPaidAmount;
    private final Integer numberOfPaidInstallments;
    private final Boolean isPaidCompletely;

    private PayInformation(Builder builder) {
        totalPaidAmount = builder.totalPaidAmount;
        numberOfPaidInstallments = builder.numberOfPaidInstallments;
        isPaidCompletely = builder.isPaidCompletely;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Money getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public Integer getNumberOfPaidInstallments() {
        return numberOfPaidInstallments;
    }

    public Boolean getPaidCompletely() {
        return isPaidCompletely;
    }


    public static final class Builder {
        private Money totalPaidAmount;
        private Integer numberOfPaidInstallments;
        private Boolean isPaidCompletely;

        private Builder() {
        }

        public Builder totalPaidAmount(Money val) {
            totalPaidAmount = val;
            return this;
        }

        public Builder numberOfPaidInstallments(Integer val) {
            numberOfPaidInstallments = val;
            return this;
        }

        public Builder isPaidCompletely(Boolean val) {
            isPaidCompletely = val;
            return this;
        }

        public PayInformation build() {
            return new PayInformation(this);
        }
    }
}
