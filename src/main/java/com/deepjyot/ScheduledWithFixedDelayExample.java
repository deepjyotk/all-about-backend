package com.deepjyot;

import java.util.concurrent.*;
import java.time.LocalTime;

public class ScheduledWithFixedDelayExample {

    public static void main(String[] args) {

        // 1) Create a ScheduledExecutorService with 3 threads
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        // 2) Schedule 30 tasks, each with a fixed delay of 5 seconds
        for (int i = 1; i <= 30; i++) {
            int taskId = i;
            scheduler.scheduleWithFixedDelay(() -> {
                        // This block is the "task" that repeats
                        String threadName = Thread.currentThread().getName();
                        LocalTime time = LocalTime.now();
                        System.out.printf("Task %d running on %s at %s%n", taskId, threadName, time);

                        // Simulate some work by sleeping 2 seconds
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    },
                    0,                // initial delay (start immediately)
                    5,                // delay after each run
                    TimeUnit.SECONDS);
        }

        // 3) Let the tasks run for, say, 30 seconds, then shut down
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 4) Shut down the scheduler
//        System.out.println("Shutting down the scheduler...");
//        scheduler.shutdown();

        // Optional: Wait for termination or forcibly shutdown
        try {
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }

        System.out.println("Scheduler shut down.");
    }
}
