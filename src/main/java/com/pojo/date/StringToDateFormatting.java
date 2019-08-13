package com.pojo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunitc on 6/2/17.
 */
public class StringToDateFormatting {


    private static String DATE_FORMAT = "MM/dd/yyyy";
    private static String DATE_FORMAT_LONG = "dd MMM yyyy";
    public static void main(String[] args) throws ParseException {
        printDates(DATE_FORMAT, new String[]{
                "06/01/2017",
                "01/03/2017",
                "12/31/2014",
                "05/09/2017 12:15:00"
        });


        printDates(DATE_FORMAT_LONG, new String[]{
                "01 December 2017",
                "03 March 2018",
                "31 December 2014",
                "5 January 2015",
                "6 Feb 2017",
                "Unknown",
        });

    }

    private static void printDates(String df, String[] dates) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(df);
        for (String s : dates) {
            Date date = format.parse(s);
            System.out.println(s + " => " + date);
        }
    }
}
