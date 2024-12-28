package com.credit.module.loan.service.dataaccess.customer.mapper;

import com.credit.module.loan.service.dataaccess.customer.entity.CustomerEntity;
import com.credit.module.loan.service.domain.entity.Customer;
import com.credit.module.loan.service.domain.entity.Money;
import com.credit.module.loan.service.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(
                new CustomerId(customerEntity.getId()),
                customerEntity.getName(),
                customerEntity.getSurname(),
                new Money(customerEntity.getCreditLimit()),
                new Money(customerEntity.getUsedCreditLimit()));
    }
}
