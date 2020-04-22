package com.javabyexamples.java.core.formatter;

import java.util.Calendar;

public class DateAndTimeFormatterTest {

    public static void main(String[] args) {
        DateAndTimeFormatterTest formatter = new DateAndTimeFormatterTest();
        formatter.date();
        formatter.time();
    }

    /**
     * A – outputs the full day of the week
     * d – outputs the two-digit day of the month
     * B – outputs the full month name
     * m – outputs the two-digit month
     * Y – outputs the year in four digits
     * y – outputs the last two digits of the year
     */
    public void date() {
        System.out.println("###### Dates");
        Calendar date = Calendar.getInstance();
        date.set(2100, 06, 25, 14, 30, 40);
        System.out.format("' %tA '%n", date);
        System.out.format("' %ta '%n", date);
        System.out.format("' %tB '%n", date);
        System.out.format("' %tb '%n", date);
        System.out.format("' %TA '%n", date);

        System.out.format("' %tm '%n", date);
        System.out.format("' %td '%n", date);
        System.out.format("' %ty '%n", date);
        System.out.format("' %tY '%n", date);

        System.out.format("' %tD '%n", date);
        System.out.format("' %tF '%n", date);
        System.out.format("' %tc '%n", date);

        System.out.format("' %1$td/%1$tm/%1$ty '%n", date);
        System.out.format("' %1$tY-%1$tm-%1$td '%n", date);
        System.out.format("' %1$ta %1$tb %1$td %1$tT %1$tZ %1$tY '%n", date);
    }

    /**
     * H - outputs the hour
     * M - outputs the minute
     * S - outputs the second
     * L - outputs the milliseconds
     * N - outputs the nanoseconds
     * p - outputs am/pm
     * z - outputs the timezone offset
     */
    public void time() {
        System.out.println("###### Time");
        Calendar date = Calendar.getInstance();
        date.set(2010, 06, 25, 14, 30, 40);

        System.out.format("' %tH '%n", date);
        System.out.format("' %tM '%n", date);
        System.out.format("' %tS '%n", date);
        System.out.format("' %tp '%n", date);

        System.out.format("' %tr '%n", date);
        System.out.format("' %tR '%n", date);
        System.out.format("' %tT '%n", date);

        System.out.format("' %1$tI:%1$tM:%1$tS %1$Tp '%n", date);
        System.out.format("' %1$tH:%1$tM '%n", date);
        System.out.format("' %1$tH:%1$tM:%1$tS '%n", date);
    }
}
