package com.credit.module.loan.service.dataaccess.loan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LoanInstallmentIdEntity.class)
@Table(name = "loan_installment")
@Entity
public class LoanInstallmentEntity {

    @Id
    private Long id;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOAN_ID")
    private LoanEntity loan;

    private BigDecimal amount;
    private BigDecimal paidAmount;
    private ZonedDateTime dueDate;
    private ZonedDateTime paymentDate;
    private Boolean isPaid;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoanInstallmentEntity that = (LoanInstallmentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(loan, that.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loan);
    }

    /*    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoanInstallmentEntity that = (LoanInstallmentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loan);
    }*/
}
