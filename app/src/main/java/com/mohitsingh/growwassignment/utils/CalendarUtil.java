package com.mohitsingh.growwassignment.utils;

import android.util.Log;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarUtil {

    // find no of days in a month of a year
    public int getNoOfDays(int month, int year) {
        // create a object of calendar and set month and year in it
        Calendar calendar = new GregorianCalendar(year, month, 1);
        // return length of yearMonth to get no of days in the provided month
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public List<Date> getDatesOfWeek(int day, int month, int year) {
        // create empty days list
        List<Date> daysOfWeek = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        // set day, month and year in the calendar
        calendar.setTime(new Date(year - 1900, month, day));

        // iterate form sunday to saturday to get days of the week
        for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
            calendar.set(Calendar.DAY_OF_WEEK, i);
            // add date in the list
            daysOfWeek.add(calendar.getTime());
        }
        // at the end return list
        return daysOfWeek;
    }

}
