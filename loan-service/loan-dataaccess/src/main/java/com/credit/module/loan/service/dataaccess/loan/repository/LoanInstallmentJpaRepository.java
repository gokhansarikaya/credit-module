package com.credit.module.loan.service.dataaccess.loan.repository;

import com.credit.module.loan.service.dataaccess.loan.entity.LoanInstallmentEntity;
import com.credit.module.loan.service.domain.valueobject.LoanInstallmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanInstallmentJpaRepository extends JpaRepository<LoanInstallmentEntity, LoanInstallmentId> {
    Optional<List<LoanInstallmentEntity>> findByLoanId(UUID value);
}
