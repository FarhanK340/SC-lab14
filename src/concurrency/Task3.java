package concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Task3 {
    public static void main(String[] args) {
        List<Integer> sharedList = new CopyOnWriteArrayList<>();

        Thread thread1 = new Thread(new AddNumbers(sharedList));
        Thread thread2 = new Thread(new AddNumbers(sharedList));
        Thread thread3 = new Thread(new AddNumbers(sharedList));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final List: " + sharedList);
    }
}

class AddNumbers implements Runnable {
    private List<Integer> sharedList;

    public AddNumbers(List<Integer> sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            sharedList.add(i);  // Adding to the shared list
            System.out.println("Added: " + i);
        }
    }
}
