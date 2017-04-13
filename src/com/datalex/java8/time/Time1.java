package com.datalex.java8.time;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by shaojie.xu on 13/04/2017.
 */
public class Time1 {


    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime theOtherDay = today.plus(5, ChronoUnit.DAYS)
                                      .plus(5, ChronoUnit.HOURS)
                                      .plus(40, ChronoUnit.MINUTES);

        Date now = new Date();
        Date tomorrowDate = Date.from(theOtherDay.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(numDaysNoTimeDiff(now, tomorrowDate));
        System.out.println(numDaysNoTimeDiffJava8(now, tomorrowDate));
    }

    // old style with SimpleDateFormat
    public static Integer numDaysNoTimeDiff(Date dateStart , Date dayEnd)
    {
        int result;
        int millSecondsInOneDay = 24 * (60 * (60 * 1000));
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String sStart = formatter.format(dateStart);
            String sEnd = formatter.format(dayEnd);
            //to have the date with no time
            dateStart = formatter.parse(sStart);
            dayEnd = formatter.parse(sEnd);
            result = (int) Math.ceil((dayEnd.getTime() - dateStart.getTime()) / millSecondsInOneDay );
        }
        catch (Exception e)
        {
            return 0;
        }
        return result;
    }

    // new one with java 8 Date api
    public static Integer numDaysNoTimeDiffJava8(Date dateStart , Date dayEnd){
        ZonedDateTime startZonedDateTime = ZonedDateTime.ofInstant(dateStart.toInstant(), ZoneId.systemDefault());
        ZonedDateTime endZonedDateTime = ZonedDateTime.ofInstant(dayEnd.toInstant(), ZoneId.systemDefault());
        return (int) ChronoUnit.DAYS.between(startZonedDateTime,endZonedDateTime);
    }
}
