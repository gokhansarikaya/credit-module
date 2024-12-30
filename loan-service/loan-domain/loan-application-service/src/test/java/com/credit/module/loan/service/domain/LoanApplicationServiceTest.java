package com.credit.module.loan.service.domain;

import com.credit.module.loan.service.domain.dto.create.CreateLoanRequest;
import com.credit.module.loan.service.domain.dto.create.CreateLoanResponse;
import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.exception.LoanDomainException;
import com.credit.module.loan.service.domain.helper.LoanHelper;
import com.credit.module.loan.service.domain.mapper.LoanDataMapper;
import com.credit.module.loan.service.domain.ports.input.service.LoanApplicationService;
import com.credit.module.loan.service.domain.ports.output.repository.CustomerRepository;
import com.credit.module.loan.service.domain.ports.output.repository.LoanInstallmentRepository;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = LoanTestConfiguration.class)
public class LoanApplicationServiceTest {

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

    private CreateLoanRequest createLoanRequest;
    private CreateLoanRequest createLoanRequestZeroAmount;
    private CreateLoanRequest createLoanRequestWrongNumberOfInstallment;
    private CreateLoanRequest createLoanRequestWrongInterestRate;

    private final UUID CUSTOMER_ID = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
    private final UUID LOAN_ID = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");


    @BeforeAll
    public void init() {
        createLoanRequest = CreateLoanRequest.builder()
                .customerId(CUSTOMER_ID)
                .amount(new BigDecimal("1000"))
                .interestRate(0.5)
                .numberOfInstalments(6)
                .build();

        createLoanRequestZeroAmount = CreateLoanRequest.builder()
                .customerId(CUSTOMER_ID)
                .amount(new BigDecimal("0"))
                .interestRate(0.5)
                .numberOfInstalments(6)
                .build();

        createLoanRequestWrongInterestRate = CreateLoanRequest.builder()
                .customerId(CUSTOMER_ID)
                .amount(new BigDecimal("100"))
                .interestRate(0.6)
                .numberOfInstalments(6)
                .build();

        createLoanRequestWrongNumberOfInstallment = CreateLoanRequest.builder()
                .customerId(CUSTOMER_ID)
                .amount(new BigDecimal("100"))
                .interestRate(0.5)
                .numberOfInstalments(5)
                .build();

        Customer customer = Customer.builder()
                .id(new CustomerId(CUSTOMER_ID))
                .name("John")
                .surname("Dao")
                .creditLimit(new Money(new BigDecimal("1500")))
                .usedCreditLimit(new Money(new BigDecimal("0")))
                .build();

        Loan loan = loanDataMapper.createLoanCommandToLoan(createLoanRequest);
        when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);
        when(loanHelper.checkLoan(LOAN_ID)).thenReturn(loan);
        when(loanHelper.checkCustomer(CUSTOMER_ID)).thenReturn(customer);
    }

    @Test
    public void testCreateLoan() {
        CreateLoanResponse createLoanResponse = loanApplicationService.createLoan(createLoanRequest);
        assertEquals("Loan Created Successfully", createLoanResponse.getMessage());
    }

    @Test
    public void testCreateLoanWithZeroAmount() {
        LoanDomainException loanDomainException = assertThrows(LoanDomainException.class,
                () -> loanApplicationService.createLoan(createLoanRequestZeroAmount));
        assertEquals("Loan amount : 0 is not valid!", loanDomainException.getMessage());
    }

    @Test
    public void testCreateLoanWithWrongInterestRate() {
        LoanDomainException loanDomainException = assertThrows(LoanDomainException.class,
                () -> loanApplicationService.createLoan(createLoanRequestWrongInterestRate));

        assertEquals("Interest rate is not in valid range!", loanDomainException.getMessage());
    }

    @Test
    public void testCreateLoanWithWrongNumberOfInstallment() {
        LoanDomainException loanDomainException = assertThrows(LoanDomainException.class,
                () -> loanApplicationService.createLoan(createLoanRequestWrongNumberOfInstallment));

        assertEquals("Installment value is not valid!", loanDomainException.getMessage());
    }

}
