package net.mefmor.edu.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreadInterruptingTest {

    private static class InfinityThread extends Thread {
        @Override
        public void run() {
            //noinspection StatementWithEmptyBody
            while (!isInterrupted()) {

            }
        }
    }

    @Test
    void testNewThreadDefaultState() {
        var thread = new InfinityThread();

        Assertions.assertEquals(Thread.State.NEW, thread.getState());
        Assertions.assertFalse(thread.isAlive());
        Assertions.assertFalse(thread.isInterrupted());
    }

    @Test
    void testStartedThread() throws InterruptedException {
        var thread = new InfinityThread();

        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
        Assertions.assertTrue(thread.isAlive());
        Assertions.assertFalse(thread.isInterrupted());

        thread.interrupt();
        thread.join();
    }

    @Test
    void testInterruptedThread() throws InterruptedException {
        var thread = new InfinityThread();

        thread.start();
        thread.interrupt();
        thread.join();

        Assertions.assertTrue(thread.isInterrupted());
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
        Assertions.assertFalse(thread.isAlive());
    }
}
