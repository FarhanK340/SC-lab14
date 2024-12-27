package concurrency;

public class Task1 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintNumbers());
        Thread thread2 = new Thread(new PrintSquares());

        thread1.start();
        thread2.start();
    }
}

class PrintNumbers implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
        }
    }
}

class PrintSquares implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Square: " + (i * i));
        }
    }
}
