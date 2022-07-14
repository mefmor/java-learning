package net.mefmor.edu.concurrent.synchronization;

class Accountant extends Thread {
    final static int INCREMENT_COUNT = 1_000_000;
    private final Counter counter;

    Accountant(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < INCREMENT_COUNT; i++) {
            counter.increment();
        }
    }
}
