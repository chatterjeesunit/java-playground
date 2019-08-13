package com.pojo.date;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by sunitc on 7/13/17.
 */
public class TimezoneFormatter {

    public static void main(String[] args) {

        printtimezones();
    }

    private static void printtimezones() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();

        for (String zoneId : zoneIds) {
            ZoneId zone = ZoneId.of(zoneId);
            ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);

            ZoneOffset zoneOffset = zonedDateTime.getOffset();
            String longName = TimeZone.getTimeZone(zoneId).getDisplayName();
//            String longName = zone.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            String offset = zoneOffset.getId().replaceAll("Z", "+00:00");
            System.out.println("(UTC " + offset + ") " + zoneId + ", " + longName);
        }
    }

}
