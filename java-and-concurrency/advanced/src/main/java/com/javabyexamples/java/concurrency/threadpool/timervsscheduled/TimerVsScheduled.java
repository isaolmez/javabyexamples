package com.javabyexamples.java.concurrency.threadpool.timervsscheduled;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerVsScheduled {

    public void repeatedTaskWithTimer() {
        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Second: " + LocalDateTime.now().getSecond());
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    public void repeatedTaskWithScheduled() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        final Runnable timerTask = () -> System.out.println("Second: " + LocalDateTime.now().getSecond());

        scheduledExecutorService.scheduleWithFixedDelay(timerTask, 0, 1, TimeUnit.SECONDS);
    }

    public void oneShotTaskWithTimer() {
        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Second: " + LocalDateTime.now().getSecond());
            }
        };
        timer.schedule(timerTask, 1000);
    }

    public void oneShotTaskWithScheduled() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        final Runnable timerTask = () -> System.out.println("Second: " + LocalDateTime.now().getSecond());

        scheduledExecutorService.schedule(timerTask, 1, TimeUnit.SECONDS);
    }

    public void exceptionInTimer() throws InterruptedException {
        final Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 0);

        TimeUnit.SECONDS.sleep(1);

        timer.schedule(new ThrowTask(), 0);
    }

    public void exceptionInScheduled() throws InterruptedException {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(new ThrowTask(), 0, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(1);

        scheduledExecutorService.schedule(new ThrowTask(), 0, TimeUnit.SECONDS);
    }

    public void longRunningWithTimer() {
        final Timer timer = new Timer();

        timer.scheduleAtFixedRate(new LongRunningTask("Task1"), 0, 1000);
        timer.scheduleAtFixedRate(new LongRunningTask("Task2"), 0, 1000);
    }

    public void longRunningWithScheduled() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        scheduledExecutorService.scheduleAtFixedRate(new LongRunningTask("Task1"), 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new LongRunningTask("Task2"), 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        final TimerVsScheduled timerVsScheduled = new TimerVsScheduled();
//        timerVsScheduled.repeatedTaskWithTimer();
//        timerVsScheduled.repeatedTaskWithScheduled();

//        timerVsScheduled.oneShotTaskWithTimer();
//        timerVsScheduled.oneShotTaskWithScheduled();

//        timerVsScheduled.exceptionInTimer();
//        timerVsScheduled.exceptionInScheduled();

//        timerVsScheduled.longRunningWithTimer();
        timerVsScheduled.longRunningWithScheduled();
    }

    static class ThrowTask extends TimerTask {

        public void run() {
            System.out.println("I will throw exception now.");
            throw new RuntimeException("Planned");
        }
    }

    static class LongRunningTask extends TimerTask {

        private final String name;

        LongRunningTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " started at: " + LocalDateTime.now().getSecond());

                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
        }
    }
}
