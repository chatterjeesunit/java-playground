package com.corejava.multithreading.synchronization;

public class SynchronizationDemoForCheckAndAct {

    static class Alarm {
        private boolean alarmRaised;

        public synchronized void raiseAlarm() {
            if(!alarmRaised) {
                System.out.println("Raising an alarm");
                alarmRaised = true;
            }
        }
    }


    public static void main(String[] args) {

        final int numThreads = 50;

        //Shared Resource
        final Alarm alarm = new Alarm();

        Runnable runnable = () -> alarm.raiseAlarm();

        Thread[] threads = new Thread[numThreads];
        // Start threads to increment the counter concurrently
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
    }
}
