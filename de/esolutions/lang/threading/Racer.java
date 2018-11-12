package de.esolutions.lang.threading;

public class Racer {
    volatile int i;
    public Racer() {
        i = 0;
    }
    public void inc() {
        for (int j = 0; j < 10_000_000; j++) {
            i++;
        }
    }
    public void incSync() {
        for (int j = 0; j < 10_000_000; j++) {
            synchronized (this) {
                i++;
            }
        }
    }
    public int get() {
        return i;
    }
    public Thread run() {
        Thread thread = new Thread(this::inc);
        thread.start();
        return thread;
    }
    public Thread runSync() {
        Thread thread = new Thread(this::incSync);
        thread.start();
        return thread;
    }
    public static void main(String[] args) throws InterruptedException {
        Racer r = new Racer();
        long startTime = System.currentTimeMillis();
        Thread t1 = r.run();
        Thread t2 = r.run();
        t1.join();
        t2.join();
        long finishTime = System.currentTimeMillis();
        System.out.println("Result = " + r.get() + "\nTime elapsed " + (finishTime - startTime) + "ms");
        startTime = System.currentTimeMillis();
        t1 = r.runSync();
        t2 = r.runSync();
        t1.join();
        t2.join();
        finishTime = System.currentTimeMillis();
        System.out.println("Result = " + r.get() + "\nTime elapsed " + (finishTime - startTime) + "ms");
    }
}
