package net.mefmor.edu.concurrent;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class ThreadProcessDemo {
    private static class CpuWaster extends Thread {
        public void run() {
            while (!interrupted()) {
                Thread.onSpinWait();
            }
        }
    }

    public static void main(String[] args) {
        // display current information about this process
        Runtime rt = Runtime.getRuntime();
        long usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);

        // start 6 new threads
        System.out.println("\nStarting 6 CPUWaster threads...\n");
        var threads = new ArrayList<CpuWaster>();
        IntStream.range(1, 5).forEach(
                (i) -> threads.add(new CpuWaster())
        );
        threads.forEach(Thread::start);

        // display current information about this process
        usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);

        threads.forEach(Thread::interrupt);
    }
}
