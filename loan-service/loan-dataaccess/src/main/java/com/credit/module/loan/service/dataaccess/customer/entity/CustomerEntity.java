package com.credit.module.loan.service.dataaccess.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Entity
public class CustomerEntity {

    @Id
    private UUID id;
    private String name;
    private String surname;
    private BigDecimal creditLimit;
    private BigDecimal usedCreditLimit;
}
