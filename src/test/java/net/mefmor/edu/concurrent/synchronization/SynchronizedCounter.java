package net.mefmor.edu.concurrent.synchronization;

class SynchronizedCounter implements Counter {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public int value() {
        return c;
    }
}
