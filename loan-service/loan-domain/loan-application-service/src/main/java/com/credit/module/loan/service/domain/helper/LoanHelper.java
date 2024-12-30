package com.credit.module.loan.service.domain.helper;

import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Loan;
import com.credit.module.loan.service.domain.exception.CustomerNotFoundException;
import com.credit.module.loan.service.domain.exception.LoanNotFoundException;
import com.credit.module.loan.service.domain.ports.output.repository.CustomerRepository;
import com.credit.module.loan.service.domain.ports.output.repository.LoanRepository;
import com.credit.module.loan.service.domain.valueobject.LoanId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class LoanHelper {
    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    public LoanHelper(CustomerRepository customerRepository, LoanRepository loanRepository) {
        this.customerRepository = customerRepository;
        this.loanRepository = loanRepository;
    }

    public Customer checkCustomer(UUID customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomer(customerId);
        if (optionalCustomer.isEmpty()) {
            log.error("Could not find customer with customer id: {}", customerId);
            throw new CustomerNotFoundException("Could not find customer with customer id: " + customerId);
        }
        return optionalCustomer.get();
    }

    public Loan checkLoan(UUID loanId) {
        Optional<Loan> loan = loanRepository.findById(new LoanId(loanId));
        if (loan.isEmpty()) {
            log.error("Could not find loan with loan id: {}", loanId);
            throw new LoanNotFoundException("Could not find loan with loan id: " + loanId);
        }
        return loan.get();
    }
}
