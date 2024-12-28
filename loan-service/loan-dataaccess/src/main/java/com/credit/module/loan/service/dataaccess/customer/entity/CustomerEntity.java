package com.credit.module.loan.service.dataaccess.customer.entity;

import com.credit.module.loan.service.dataaccess.loan.entity.LoanEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
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


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<LoanEntity> loans;
}
