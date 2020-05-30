package com.play.corejava.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunitc on 6/1/17.
 */
public class RegexTest01 {

    public static void main(String[] args) {

        numbersMatch();

        String[] urls = {
                "http://dummy-dev-site/data/huntley-IL/jeff-aurand/285917",
                "https://prod-site/data/san-rafael-CA/john-mcnertney/264870"
        };
        matchURL(urls);

    }

    private static void numbersMatch() {


        String[] testInput = {
                "01", "1", "1.1", "1.01", "01.01.03.04", "01.a", "31.01.13"};
        Pattern pattern = Pattern.compile("[\\d]{2}([.][\\d]{2})*");
        Arrays.stream(testInput).forEach(s ->
                System.out.println(s + "\t: " + pattern.matcher(s).matches()));
    }

    private static void matchURL(String[] urls) {
        String regex = "(http[s]?:\\/\\/)([\\w-.]*)/data/([\\w-]*)-([\\w]{2})/(.*)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Arrays.stream(urls)
                .forEach(url -> {
                    Matcher match = pattern.matcher(url);
                    if(match.matches() && match.groupCount() == 5) {
                        System.out.println("City = " + match.group(3) + ", " +
                                "State = " + match.group(4));
                    }
                });
    }

}
