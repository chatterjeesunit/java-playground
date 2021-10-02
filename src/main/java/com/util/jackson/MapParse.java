package com.util.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class MapParse {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z");

    public static void main(String[] args) throws IOException {
        long currentTimeinMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeinMillis);
        Map<String, Object> map = Map.of(
                "aaa", 111,
                "bbb", 222l,
                "ccc", date
        );

        System.out.println(currentTimeinMillis);
        System.out.println(map);

        ObjectMapper mapper = new ObjectMapper();
//        mapper.setDateFormat(df);

        final String json = mapper.writeValueAsString(map);

        System.out.println("------");
        System.out.println(json);

        Map newMap = mapper.readValue(json, Map.class);

        System.out.println("*********");
        System.out.println(newMap);

        System.out.println("...........");
        final int i = (int)newMap.get("aaa");
        final long l = ((Number) newMap.get("bbb")).longValue();
//        final Date d = parseDate((String)newMap.get("ccc"));
        System.out.println(i);
        System.out.println(l);
//        System.out.println(d);
    }

    private static Date parseDate(String dateStr) {

        df.setTimeZone(TimeZone.getDefault());
//        System.out.println("Default timezone : " + TimeZone.getDefault());

        try {
            Date date = df.parse(dateStr);
            return date;
        } catch (ParseException e) {
            System.out.println("Error parsing date : " +  e);
        }
        return null;
    }
}
