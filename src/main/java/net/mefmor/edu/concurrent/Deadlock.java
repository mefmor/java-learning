package net.mefmor.edu.concurrent;

/**
 * Demo for The Java Tutorial:
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html">Deadlock</a>
 */
public class Deadlock {
    record Friend(String name) {
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!%n", this.name, bower.name());
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.name());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston =  new Friend("Gaston");
        Thread alphonseThread = new Thread(() -> alphonse.bow(gaston));
        Thread gastonThread = new Thread(() -> gaston.bow(alphonse));
        alphonseThread.start();
        gastonThread.start();
        gastonThread.join(200);

        System.out.println("Alphonse is " + alphonseThread.getState());
        System.out.println("Gaston is " + gastonThread.getState());
    }
}
