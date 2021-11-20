package com.marimekko.swap.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component("viewUtil")
public class ViewUtil {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    public String toFirstDayOfMonth(Instant instant) {
        return YearMonth.from(instant.atZone(ZoneId.systemDefault())).atDay(1).format(formatter);
    }

    public String a() {
        return "a";
    }
}
