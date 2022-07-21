package net.mefmor.edu.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    private static class RecursiveSum extends RecursiveTask<Long> {
        private final long lowestValue, highestValue;

        public RecursiveSum(long lowestValue, long highestValue) {
            this.lowestValue = lowestValue;
            this.highestValue = highestValue;
        }

        @Override
        protected Long compute() {
            long baseCaseThreshold = 100_000;
            if ((highestValue - lowestValue) <= baseCaseThreshold) {
                long total = 0;
                for (long i = lowestValue; i <= highestValue; i++) {
                    total += i;
                }
                return total;
            } else {
                long middleIndexForSplit = (highestValue + lowestValue) / 2;
                RecursiveSum left = new RecursiveSum(lowestValue, middleIndexForSplit);
                RecursiveSum right = new RecursiveSum(middleIndexForSplit + 1, highestValue);
                left.fork();
                return right.compute() + left.join(); // current thread computes right half
            }
        }
    }

    @Test
    void testGetSum() {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        Long total = pool.invoke(new RecursiveSum(0, 1_000_000_000));
        pool.shutdown();

        Assertions.assertEquals(500000000500000000L, total);
    }
}
