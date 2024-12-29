package com.credit.module.loan.service.domain.utility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.credit.module.loan.service.domain.constants.DomainConstants.UTC;

public class DateUtility {

    public static ZonedDateTime addMonth(ZonedDateTime zonedDateTime, Integer periodLenght) {
        return zonedDateTime.plusMonths(periodLenght);
    }

    public static ZonedDateTime endOfTheDay(ZonedDateTime zonedDateTime) {
        LocalDate localDate = zonedDateTime.toLocalDate();
        return localDate.atTime(23, 59, 59).atZone(ZoneId.of(UTC));
    }

    public static ZonedDateTime firstDayOfMonth(ZonedDateTime zonedDateTime) {
        LocalDate localDate = zonedDateTime.toLocalDate();
        return localDate.withDayOfMonth(1).atTime(LocalTime.MIDNIGHT).atZone(ZoneId.of(UTC));
    }
}
