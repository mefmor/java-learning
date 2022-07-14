package net.mefmor.edu.concurrent.synchronization;

public class SynchronizedStatementCounter implements Counter {
    private int c = 0;
    @Override
    public void increment() {
        synchronized (this) {
            c++;
        }
    }

    @Override
    public int value() {
        return c;
    }
}
