package com.credit.module.loan.service.dataaccess.loan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
@Entity
public class LoanEntity {

    @Id
    private UUID id;
    private UUID customerId;
    private BigDecimal loanAmount;
    private Integer numberOfInstallment;
    private ZonedDateTime createDate;
    private Boolean isPaid;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<LoanInstallmentEntity> loanInstallments;
}
