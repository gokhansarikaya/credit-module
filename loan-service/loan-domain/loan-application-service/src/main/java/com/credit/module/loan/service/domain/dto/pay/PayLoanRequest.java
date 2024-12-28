package com.credit.module.loan.service.domain.dto.pay;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PayLoanRequest {
    @NotNull
    private final UUID loanId;
    @NotNull
    private final BigDecimal amount;
}
