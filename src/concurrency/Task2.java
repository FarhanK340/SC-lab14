package concurrency;

public class Task2 {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(new IncrementTask(counter));
        Thread t2 = new Thread(new IncrementTask(counter));
        Thread t3 = new Thread(new IncrementTask(counter));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + counter.getValue());
    }
}

class Counter {
    private int value = 0;

    public synchronized void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }
}

class IncrementTask implements Runnable {
    private final Counter counter;

    public IncrementTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            counter.increment();
        }
    }
}
