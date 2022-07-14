package net.mefmor.edu.concurrent.synchronization;

class SimpleCounter implements Counter {
    private int c = 0;

    public void increment() {
        c++;
    }

    public int value() {
        return c;
    }
}
