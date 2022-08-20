package com.ykb.annualleave.annualleaveservices.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    /**
     * turns the weekend day
     * @param ld
     * @return
     */
    public static Integer isWeekend(final LocalDate ld, final LocalDate endDate) {
        Integer dayCount=0;

        for (LocalDate date = ld; date.isBefore(endDate); date = date.plusDays(1)) {

            DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));

            if(day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY){
                dayCount++;
            }

        }


        return dayCount;
    }

    /**
     * Personal get working year
     * @param start_date
     * @param end_date
     * @return
     */
    public static long getWorkingYear(String start_date,
                                         String end_date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long differenceInYears=0L;
        try {
            Date startDate = simpleDateFormat.parse(start_date);
            Date endDate = simpleDateFormat.parse(end_date);
            long differenceInTime
                    = endDate.getTime() - startDate.getTime();
             differenceInYears
                    = TimeUnit
                    .MILLISECONDS
                    .toDays(differenceInTime)
                    / 365l;

        }
        catch (ParseException e){

        }
        return differenceInYears;

    }

    /**
     * Personal Working First Year
     * @param jobStartDate
     * @return
     */
    public static boolean isWorkingFirstYear(LocalDate jobStartDate){
        return ChronoUnit.DAYS.between(jobStartDate, LocalDate.now()) > 365;
    }

    public static double getWorkingAnnualLeaveDayNumber(String start_date,
                                      String end_date) throws ParseException {

        Date dateStart=new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
        Date dateFinish=new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
        var DifferenceInTime = dateFinish.getTime() - dateStart.getTime();

        double DifferenceInDays = DifferenceInTime / (1000 * 3600 * 24);

        return DifferenceInDays;

    }
}
