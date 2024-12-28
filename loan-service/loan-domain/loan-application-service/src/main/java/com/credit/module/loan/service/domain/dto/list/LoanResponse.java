package com.credit.module.loan.service.domain.dto.list;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoanResponse {
    @NotNull
    private final UUID id;
    @NotNull
    private final BigDecimal loanAmount;
    @NotNull
    private final Integer numberOfInstallments;
    @NotNull
    private final ZonedDateTime createDate;
    @NotNull
    private final Boolean isPaid;
}
