package com.credit.module.loan.service.dataaccess.customer.mapper;

import com.credit.module.loan.service.dataaccess.customer.entity.CustomerEntity;
import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(new CustomerId(customerEntity.getId()))
                .name(customerEntity.getName())
                .surname(customerEntity.getSurname())
                .creditLimit(new Money(customerEntity.getCreditLimit()))
                .usedCreditLimit(new Money(customerEntity.getUsedCreditLimit()))
                .build();
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .name(customer.getName())
                .surname(customer.getSurname())
                .creditLimit(customer.getCreditLimit().getAmount())
                .usedCreditLimit(customer.getUsedCreditLimit().getAmount())
                .build();
    }
}
