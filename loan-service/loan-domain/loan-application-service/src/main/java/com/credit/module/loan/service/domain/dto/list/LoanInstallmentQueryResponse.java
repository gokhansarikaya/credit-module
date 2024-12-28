package com.credit.module.loan.service.domain.dto.list;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class LoanInstallmentQueryResponse {
    List<LoanInstallmentResponse> loanInstallments;
}
