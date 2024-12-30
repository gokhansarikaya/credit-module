package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.dto.create.CreateLoanCommand;
import com.credit.module.loan.service.domain.dto.pay.PayLoanRequest;
import com.credit.module.loan.service.domain.dto.pay.PayLoanResponse;
import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.helper.LoanHelper;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.input.service.LoanApplicationService;
import com.credit.module.loan.service.domain.ports.output.repository.CustomerRepository;
import com.credit.module.loan.service.domain.ports.output.repository.LoanInstallmentRepository;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import com.credit.module.loan.service.domain.valueobject.LoanId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = LoanTestConfiguration.class)
public class LoanApplicationServicePayTest {
    @Autowired
    LoanApplicationService loanApplicationService;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LoanInstallmentRepository loanInstallmentRepository;

    @Autowired
    LoanDataMapper loanDataMapper;

    @Autowired
    LoanHelper loanHelper;

    private CreateLoanCommand createLoanCommand;
    private CreateLoanCommand createLoanCommandWrongNumberOfInstallment;
    private CreateLoanCommand createLoanCommandWrongInterestRate;
    private Loan loan;

    private final UUID CUSTOMER_ID = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
    private final UUID LOAN_ID = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");

    @Test
    public void testPayLoan() {
        when(loanRepository.findById(new LoanId(LOAN_ID))).thenReturn(Optional.of(getLoan(new BigDecimal("1000"))));
        when(loanHelper.checkLoan(LOAN_ID)).thenReturn(getLoan(new BigDecimal("1000")));
        PayLoanResponse payLoanResponse = loanApplicationService.payLoan(getPayLoanRequest(new BigDecimal(500)));
        assertEquals(Boolean.FALSE, payLoanResponse.getIsPaidCompletely());
        assertEquals(2, payLoanResponse.getPaidInstallments());
    }

    @Test
    public void testPayLoanWithFullAmount() {
        when(loanRepository.findById(new LoanId(LOAN_ID))).thenReturn(Optional.of(getLoan(new BigDecimal("1000"))));
        when(loanHelper.checkLoan(LOAN_ID)).thenReturn(getLoan(new BigDecimal("1000")));
        PayLoanResponse payLoanResponse = loanApplicationService.payLoan(getPayLoanRequest(new BigDecimal(1500)));
        assertEquals(Boolean.FALSE, payLoanResponse.getIsPaidCompletely());
        assertEquals(2, payLoanResponse.getPaidInstallments());
    }

    @Test
    public void testPayLoanWithZeroAmount() {
        when(loanRepository.findById(new LoanId(LOAN_ID))).thenReturn(Optional.of(getLoan(new BigDecimal("1000"))));
        when(loanHelper.checkLoan(LOAN_ID)).thenReturn(getLoan(new BigDecimal("1000")));
        PayLoanResponse payLoanResponse = loanApplicationService.payLoan(getPayLoanRequest(new BigDecimal(0)));
        assertEquals(Boolean.FALSE, payLoanResponse.getIsPaidCompletely());
        assertEquals(0, payLoanResponse.getPaidInstallments());
    }

    private Loan getLoan(BigDecimal amount) {
        Loan loan = Loan.builder()
                .loanAmount(new Money(amount))
                .numberOfInstallment(6)
                .interestRate(0.5)
                .customer(Customer.builder()
                        .id(new CustomerId(CUSTOMER_ID))
                        .name("John")
                        .surname("Dao")
                        .creditLimit(new Money(new BigDecimal("2500")))
                        .usedCreditLimit(Money.ZERO)
                        .build())
                .build();
        loan.initializeLoan();
        loan.setId(new LoanId(LOAN_ID));
        return loan;
    }

    private PayLoanRequest getPayLoanRequest(BigDecimal payamount) {
        return PayLoanRequest.builder()
                .loanId(LOAN_ID)
                .amount(payamount)
                .build();
    }

}
