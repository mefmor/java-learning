package net.mefmor.edu.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Demo for The Java Tutorial:
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html">Defining and Starting a Thread</a>
 */
public class DefiningAndStartingAThreadTest {
    private static String greeting;

    private static class MyThread extends Thread {
        @Override
        public void run() {
            greeting = "Hello from a thread!";
        }
    }

    @Test
    void testStartingCreatedThread() throws InterruptedException {
        var thread = new MyThread();

        thread.start();
        thread.join();

        Assertions.assertEquals("Hello from a thread!", greeting);
    }

    @Test
    void testStartingThreadFromRunnable() throws InterruptedException {
        Runnable r = () -> greeting = "Hello from a thread!";
        var thread = new Thread(r);

        thread.start();
        thread.join();

        Assertions.assertEquals("Hello from a thread!", greeting);
    }


}
