package com.credit.module.loan.service.domain.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DomainConstants {
    public DomainConstants() {
    }

    public static final Double MIN_INTEREST_RATE = 0.1;
    public static final Double MAX_INTEREST_RATE = 0.5;

    public static final Integer PAYABLE_MONTH_RANGE = 3;

    public static final List<Integer> INSTALLMENT_VALUES = new ArrayList<>(Arrays.asList(6, 9, 12, 24));

    public static final String UTC = "UTC";

}
