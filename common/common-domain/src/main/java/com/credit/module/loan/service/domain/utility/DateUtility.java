package com.credit.module.loan.service.domain.utility;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

import static com.credit.module.loan.service.domain.constants.DomainConstants.UTC;

public class DateUtility {
    public static ZonedDateTime endOfTheFirstDayOfMonth(long time, Integer periodLength) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.add(Calendar.MONTH, periodLength);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return ZonedDateTime.ofInstant(calendar.toInstant(), ZoneId.of(UTC));
    }

    public static ZonedDateTime firstDayOfMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);// if you want to make sure you're at midnight
        return ZonedDateTime.ofInstant(calendar.toInstant(), ZoneId.of(UTC));
    }

    public static ZonedDateTime lastPayableDate(ZonedDateTime date, Integer monthRange) {
        return endOfTheFirstDayOfMonth(firstDayOfMonth(date.toInstant().toEpochMilli()).toInstant().toEpochMilli(), monthRange);
    }
}
