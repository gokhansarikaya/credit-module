package com.credit.module.loan.service.dataaccess.loan.entity;

import com.credit.module.loan.service.dataaccess.customer.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    private BigDecimal loanAmount;
    private Integer numberOfInstallment;
    private ZonedDateTime createDate;
    private Boolean isPaid;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<LoanInstallmentEntity> loanInstallments;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoanEntity that = (LoanEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
