package com.marimekko.swap.utils;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class MonthUtils {
    public static Instant monthStart(Instant instant) {
        return YearMonth.from(instant.atZone(ZoneId.systemDefault())).atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public static long getMonthsBetweenInstants(Instant start, Instant end) {
        return ChronoUnit.MONTHS.between(YearMonth.from(start.atZone(ZoneId.of("UTC"))), YearMonth.from(end.atZone(ZoneId.of("UTC"))));
    }

    public static Instant plusMonth(Instant instant, int months) {
        return Instant.now().atZone(ZoneId.of("UTC")).plus(1, java.time.temporal.ChronoUnit.MONTHS).toInstant();
    }
}
