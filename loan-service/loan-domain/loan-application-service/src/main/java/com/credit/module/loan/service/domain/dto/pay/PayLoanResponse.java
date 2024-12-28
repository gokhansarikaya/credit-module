package com.credit.module.loan.service.domain.dto.pay;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class PayLoanResponse {
    @NotNull
    private final Integer paidInstallments;
    @NotNull
    private final BigDecimal totalAmountSpent;
    @NotNull
    private final Boolean isPaidCompletely;
}
