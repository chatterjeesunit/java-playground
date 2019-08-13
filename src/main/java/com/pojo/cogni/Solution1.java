package com.pojo.cogni;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by sunitc on 4/3/18.
 */
public class Solution1 {

    final static SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd MMM yyyy");
    final static SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
        String[] input = new String[]{
                "20th Oct 2052",
                "6th Jun 1933",
                "26th May 1960",
                "20th Sep 1958",
                "16th Mar 2068",
                "25th May 1912",
                "16th Dec 2018",
                "26th Dec 2061",
                "4th Nov 2030",
                "28th Jul 1963"
        };

        Arrays.stream(reformatDate(input)).forEach(d -> System.out.println(d));

    }

    static String[] reformatDate(String[] dates) {


        return Arrays.stream(dates).map(strDate -> {
            try {
                String newDate = strDate.replaceFirst("([\\d]+)([\\w]+[\\s]+)(.*)", "$1 $3");
                return targetDateFormat.format(inputDateFormat.parse(newDate));
            } catch (ParseException e) {
                return null;
            }
        }).collect(Collectors.toList()).toArray(new String[0]);
    }
}
