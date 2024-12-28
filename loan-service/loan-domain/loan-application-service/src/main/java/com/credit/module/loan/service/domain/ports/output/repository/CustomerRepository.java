package com.credit.module.loan.service.domain.ports.output.repository;

import com.credit.module.loan.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
