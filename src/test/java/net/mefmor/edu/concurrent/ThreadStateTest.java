package net.mefmor.edu.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadStateTest {

    @Test
    void testStates() throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        assertEquals(Thread.State.NEW, thread.getState());
        assertFalse(thread.isAlive());

        thread.start();
        assertEquals(Thread.State.RUNNABLE, thread.getState());
        assertTrue(thread.isAlive());

        MyThread.isActive = false;
        thread.join();
        assertEquals(Thread.State.TERMINATED, thread.getState());
        assertFalse(thread.isAlive());
    }

    static class MyThread implements Runnable {
        public static volatile boolean isActive = true;

        @Override
        public void run() {
            while (isActive) {
                Thread.onSpinWait();
            }
        }
    }
}
