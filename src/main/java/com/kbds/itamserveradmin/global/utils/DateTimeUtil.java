package com.kbds.itamserveradmin.global.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    // string(YYYY-MM-DD) -> localDateTime
    public static LocalDateTime stringToLocalDate(String stringDate) {
        return LocalDateTime.parse(stringDate, DateTimeFormatter.ISO_DATE_TIME);
    }
}
