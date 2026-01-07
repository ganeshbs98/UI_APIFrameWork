package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final Logger logger= LogManager.getLogger(DateTimeUtil.class);

    public DateTimeUtil(){

    }

    public static String getDateTimeWithAgo(int daysAgo){
        logger.info("Getting date time with "+daysAgo+" days ago from current date time");
        return Instant.now().minus(daysAgo, ChronoUnit.DAYS).toString();
    }
}
