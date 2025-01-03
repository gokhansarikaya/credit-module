package com.credit.module.loan.service.dataaccess.loan.repository;

import com.credit.module.loan.service.dataaccess.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanJpaRepository extends JpaRepository<LoanEntity, UUID> {
    Optional<List<LoanEntity>> findByCustomerId(UUID customerId);
}
