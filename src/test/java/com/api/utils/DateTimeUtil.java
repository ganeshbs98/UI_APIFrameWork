package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    public DateTimeUtil(){

    }

    public static String getDateTimeWithAgo(int daysAgo){
        return Instant.now().minus(daysAgo, ChronoUnit.DAYS).toString();
    }
}
