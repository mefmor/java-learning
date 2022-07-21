package net.mefmor.edu.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

class FutureTest {

    @Test
    void testExecuteCallableInterface() throws ExecutionException, InterruptedException {

        class AnswerToUltimateQuestionOfLiveUniverseAndEverything implements Callable<Integer> {
            @Override
            public Integer call() {
                return 42;
            }
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> answer = executor.submit(new AnswerToUltimateQuestionOfLiveUniverseAndEverything());

        Assertions.assertFalse(answer.isDone());
        Thread.sleep(100);
        Assertions.assertTrue(answer.isDone());
        Assertions.assertEquals(42, answer.get());
    }

    @Test
    void testInterruptThread() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> thinkUntilInterrupted = () -> {
            while (!Thread.interrupted()) {
                Thread.onSpinWait();
            }
            return "We will never see this result ((";
        };

        Future<String> result = executor.submit(thinkUntilInterrupted);
        Thread.sleep(100);

        Assertions.assertFalse(result.isDone(), "Shouldn't be completed yet");
        result.cancel(true);
        Assertions.assertTrue(result.isDone());
        Assertions.assertThrows(CancellationException.class, result::get);
    }
}
