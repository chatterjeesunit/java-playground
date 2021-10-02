package com.corejava.string;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sunitc on 7/19/17.
 */
public class Splitter {

    public static void main(String[] args) {

//        splitOne();
        splitTwo();
    }

    private static void splitOne() {
        String text = "Sunit|Chatterjee|Tingre Nagar, Pune|411015";
        char csvDelimiter = '|';
        final String delimiter = "\\" + csvDelimiter;
        String[] arr = text.split(delimiter);

        for (String s : arr) {
            System.out.println(s);
        }
    }


    private static void splitTwo() {

        String[] text = new String[]{
                "",
                "db.server.status1",
                "db.server.status2[]",
                "db.server.status3[aaa, bbb, ccc ]",
                "db.server.status4[xxx, , zz "
        };


        for (int i = 0; i < text.length; i++) {
            String[] info = text[i].split("\\[|\\]|,");
            List<Object> list = Arrays.asList(ArrayUtils.subarray(info, 1, info.length));

//            String finalText = list.stream().collect(Collectors.joining("|"));

            System.out.println(info[0] + " : " + list);

        }

    }
}
