package com.pojo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunitc on 6/1/18.
 */
public class RegexTest02 {

    public static void main(String[] args) {
        String templateTypeRegex = "([\\w\\s\\(\\)]*)";
        String separatorRegex = "[\\s]*[\\–\\-][\\s]*";
        String countryNameRegex = "([\\w\\s]*)";
        String remainderTitleRegex = "[\\–/\\-\\w\\d\\s\\(\\);:']*";

        String title = "Legislative Update – Hong Kong – New Specifications for IR56B / IR56E / IR56F / IR56G; Effective 1 April 2018";
        Pattern titlePattern = Pattern.compile(templateTypeRegex + separatorRegex + countryNameRegex + separatorRegex + remainderTitleRegex, Pattern.CASE_INSENSITIVE);
        System.out.println(titlePattern.toString());
        Matcher titleMatcher = titlePattern.matcher(title);
        if(titleMatcher.matches()) {
            System.out.println(titleMatcher.group(2).trim());
        }
    }
}
