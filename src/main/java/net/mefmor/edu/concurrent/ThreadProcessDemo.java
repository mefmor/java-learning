package net.mefmor.edu.concurrent;

import java.util.ArrayList;

public class ThreadProcessDemo {
    private static class CpuWaster extends Thread {
        public volatile boolean isActive = true;

        public void run() {
            while (isActive) {
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
        for (int i = 0; i < 6; i++) {
            threads.add(new CpuWaster());
        }
        threads.forEach(Thread::start);

        // display current information about this process
        usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);

        threads.forEach(t -> t.isActive = false);
    }
}
