package com.credit.module.loan.service.domain.dto.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class LoanQueryResponse {
    List<LoanResponse> loanResponses;
}
