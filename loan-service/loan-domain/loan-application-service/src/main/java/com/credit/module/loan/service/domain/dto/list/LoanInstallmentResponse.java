package com.credit.module.loan.service.domain.dto.list;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoanInstallmentResponse {
    @NotNull
    private final Long id;
    @NotNull
    private final BigDecimal amount;
    private final BigDecimal paidAmount;
    private final ZonedDateTime dueDate;
    private final ZonedDateTime paymentDate;
    private final Boolean ispaid;
}
