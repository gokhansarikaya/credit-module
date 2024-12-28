package com.credit.module.loan.service.application.rest;

import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanInstallmentQueryResponse;
import com.credit.module.loan.service.domain.dto.list.LoanQueryRequest;
import com.credit.module.loan.service.domain.dto.list.LoanQueryResponse;
import com.credit.module.loan.service.domain.dto.pay.PayLoanRequest;
import com.credit.module.loan.service.domain.dto.pay.PayLoanResponse;
import com.credit.module.loan.service.domain.ports.input.service.LoanApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/loans", produces = "application/vnd.api.v1+json")
public class LoanController {

    private final LoanApplicationService loanApplicationService;

    public LoanController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateLoanResponse> createLoan(@RequestBody CreateLoanCommand createLoanCommand) {
        log.info("Creating loan");
        CreateLoanResponse createLoanResponse = loanApplicationService.createLoan(createLoanCommand);
        log.info("Loan created");
        return ResponseEntity.ok(createLoanResponse);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<LoanQueryResponse> getLoans(@PathVariable UUID customerId) {
        LoanQueryResponse loanQueryResponse = loanApplicationService.getLoans(LoanQueryRequest.builder().customerId(customerId).build());
        return ResponseEntity.ok(loanQueryResponse);
    }

    @GetMapping("installment/{loanId}")
    public ResponseEntity<LoanInstallmentQueryResponse> getLoanInstallments(@PathVariable UUID loanId) {
        LoanInstallmentQueryResponse loanInstallmentQueryResponse = loanApplicationService.getLoanInstallments(LoanInstallmentQueryRequest.builder().loanId(loanId).build());
        return ResponseEntity.ok(loanInstallmentQueryResponse);
    }

    @PostMapping("/pay")
    public ResponseEntity<PayLoanResponse> payLoan(@RequestBody PayLoanRequest payLoanRequest) {
        PayLoanResponse payLoanResponse = loanApplicationService.payLoan(payLoanRequest);
        return ResponseEntity.ok(payLoanResponse);
    }
}
