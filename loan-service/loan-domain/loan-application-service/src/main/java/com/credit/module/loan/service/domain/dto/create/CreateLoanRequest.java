package com.credit.module.loan.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateLoanRequest {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final BigDecimal amount;
    @NotNull
    private final Double interestRate;
    @NotNull
    private final Integer numberOfInstalments;
}
