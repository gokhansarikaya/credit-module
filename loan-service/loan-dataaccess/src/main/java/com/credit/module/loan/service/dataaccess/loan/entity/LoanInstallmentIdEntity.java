package com.credit.module.loan.service.dataaccess.loan.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanInstallmentIdEntity {
    private Long id;
    private LoanEntity loan;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoanInstallmentIdEntity that = (LoanInstallmentIdEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(loan, that.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loan);
    }
}
