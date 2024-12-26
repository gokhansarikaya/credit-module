package com.credit.module.loan.service.domain.valueobject;

import java.util.UUID;

public class LoanId extends BaseId<UUID> {
    public LoanId(UUID value) {
        super(value);
    }
}
