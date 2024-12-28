package com.credit.module.loan.service.dataaccess.loan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan_installment")
@Entity
public class LoanInstallmentEntity {

    @Id
    private UUID id;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOAN_ID")
    private LoanEntity loan;

    private BigDecimal amount;
    private BigDecimal paidAmount;
    private ZonedDateTime dueDate;
    private ZonedDateTime paymentDate;
    private Boolean isPaid;
}
