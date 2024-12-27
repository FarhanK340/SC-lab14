package concurrency;

import java.util.Random;

public class Task4 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Thread client1 = new Thread(new Client(account));
        Thread client2 = new Thread(new Client(account));
        Thread client3 = new Thread(new Client(account));

        client1.start();
        client2.start();
        client3.start();

        try {
            client1.join();
            client2.join();
            client3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Account Balance: " + account.getBalance());
    }
}

class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", Balance: " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", Balance: " + balance);
        } else {
            System.out.println("Insufficient Balance for Withdrawal of: " + amount);
        }
    }

    public int getBalance() {
        return balance;
    }
}

class Client implements Runnable {
    private final BankAccount account;
    private final Random random = new Random();

    public Client(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int amount = random.nextInt(200);
            if (random.nextBoolean()) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        }
    }
}
