package net.mefmor.edu.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class WaitingThreadStatesTest {

    @Test
    void testTimedWaitingStatus() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        TimeUnit.SECONDS.sleep(1);

        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

}
