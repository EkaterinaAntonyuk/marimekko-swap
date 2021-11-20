package com.marimekko.swap.utils;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;

public class MonthUtils {
    public static Instant monthStart(Instant instant) {
        return YearMonth.from(instant.atZone(ZoneId.systemDefault())).atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}
