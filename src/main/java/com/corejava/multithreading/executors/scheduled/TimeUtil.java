package com.corejava.multithreading.executors.scheduled;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String currentTime() {
       return timeFormatter.format(LocalTime.now());
    }
}
