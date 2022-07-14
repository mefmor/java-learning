package net.mefmor.edu.concurrent.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter {
    private final AtomicInteger c = new AtomicInteger(0);
    @Override
    public void increment() {
        c.incrementAndGet();
    }

    @Override
    public int value() {
        return c.get();
    }
}
