package net.mefmor.edu.concurrent.synchronization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

class ThreadSynchronizationTest {
    private static final int THREADS_COUNT = 10;
    private static final int EXPECTED_COUNTER_VALUE = Accountant.INCREMENT_COUNT * THREADS_COUNT;

    @Test
    void testConsistencyError() throws InterruptedException {
        var counter = new SimpleCounter();

        executeAccountThreads(counter);

        Assertions.assertNotEquals(EXPECTED_COUNTER_VALUE, counter.value());
    }

    @Test
    void testSynchronizedMethod() throws InterruptedException {
        var counter = new SynchronizedCounter();

        executeAccountThreads(counter);

        Assertions.assertEquals(EXPECTED_COUNTER_VALUE, counter.value());
    }

    @Test
    void testSynchronizedStatement() throws InterruptedException {
        var counter = new SynchronizedStatementCounter();

        executeAccountThreads(counter);

        Assertions.assertEquals(EXPECTED_COUNTER_VALUE, counter.value());
    }

    @Test
    void testAtomic() throws InterruptedException {
        var counter = new AtomicCounter();

        executeAccountThreads(counter);

        Assertions.assertEquals(EXPECTED_COUNTER_VALUE, counter.value());
    }

    private void executeAccountThreads(Counter counter) throws InterruptedException {
        var threads = new ArrayList<Thread>();
        IntStream.range(0, THREADS_COUNT).forEach(
                (i) -> threads.add(new Accountant(counter))
        );

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }

}
