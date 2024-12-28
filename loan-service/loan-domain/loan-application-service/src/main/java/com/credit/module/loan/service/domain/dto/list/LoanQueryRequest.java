package com.credit.module.loan.service.domain.dto.list;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class LoanQueryRequest {
    @NotNull
    private final UUID customerId;
}
