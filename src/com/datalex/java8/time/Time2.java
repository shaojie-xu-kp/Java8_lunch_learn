package com.datalex.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by shaojie.xu on 13/04/2017.
 */
public class Time2 {

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.of(2017, Month.APRIL, 29, 12, 0, 0);

        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek);

        Month month = localDateTime.getMonth();
        System.out.println(month);

        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("2017-Apr-28 07:13", formatter);
        String string = parsed.format(formatter);
        System.out.println(string);
    }


}
