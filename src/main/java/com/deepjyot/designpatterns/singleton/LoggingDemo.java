package com.deepjyot.designpatterns.singleton;
import java.time.Duration;

class LoggingService {

    // Volatile ensures that changes made to the instance are visible to all threads
    private static volatile LoggingService instance;

    // Private constructor to prevent instantiation from other classes
    private LoggingService() {}


    // Public method to provide the singleton instance
    public static LoggingService getInstance() {
        if (instance == null) {  // First check (without lock) to improve performance
            synchronized (LoggingService.class) {  // Lock only when necessary
                if (instance == null) {  // Second check (inside lock) to ensure only one instance is created
                    instance = new LoggingService();
                }
            }
        }
        return instance;
    }

    // Sample logging methods
    public void logInfo(String message) {
        System.out.println("INFO: " + message);
    }

    public void logError(String message) {
        System.out.println("ERROR: " + message);
    }

    public void logDebug(String message) {
        System.out.println("DEBUG: " + message);
    }
}


public class LoggingDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = ()->{
            try {
                Thread.sleep(Duration.ofSeconds(4));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LoggingService loggingService = LoggingService.getInstance() ;
            loggingService.logInfo("Loggging some message");
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

//        thread1.setDaemon(true);

        thread1.start();
        thread1.join();

//        thread2.start();
//        thread2.setDaemon(true);
//        thread3.start();
//        thread3.setDaemon(true);
        System.out.println("Main thread finished execution!"); // May print before t1, t2 finish

    }
}
