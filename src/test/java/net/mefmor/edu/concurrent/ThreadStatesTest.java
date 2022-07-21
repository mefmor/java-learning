package net.mefmor.edu.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;


public class ThreadStatesTest {

    private final Thread thread = new Thread(() -> {
        while (!Thread.interrupted()) {
            Thread.onSpinWait();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });

    @Test
    void testNewThreadDefaultState() {
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
        Assertions.assertFalse(thread.isAlive());
        Assertions.assertFalse(thread.isInterrupted());
    }

    @Test
    void testStartedThread() throws InterruptedException {
        thread.start();

        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
        Assertions.assertTrue(thread.isAlive());
        Assertions.assertFalse(thread.isInterrupted());

        thread.interrupt();
        thread.join();
    }

    @Test
    void testInterruptedThread() throws InterruptedException {
        thread.start();

        thread.interrupt();

        Assertions.assertTrue(thread.isInterrupted());
        thread.join();
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
        Assertions.assertFalse(thread.isAlive());
        Assertions.assertFalse(thread.isInterrupted());
    }

    @Test
    void testTimedWaitingStatus() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);

        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

    private static class ThreadWithCommonResource extends Thread {
        @Override
        public void run() {
            commonResource();
        }

        private static synchronized void commonResource() {
            while (!Thread.interrupted()) {
                Thread.onSpinWait();
            }
        }
    }

    @Test
    void testBlockThreadState() throws InterruptedException {
        Thread t1 = new ThreadWithCommonResource();
        Thread t2 = new ThreadWithCommonResource();

        t1.start();
        t2.start();
        t1.join(200);
        t2.join(200);

        Assertions.assertEquals(Thread.State.RUNNABLE, t1.getState());
        Assertions.assertEquals(Thread.State.BLOCKED, t2.getState());
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();
    }


}
