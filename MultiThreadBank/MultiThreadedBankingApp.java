package MultiThreadBank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private double balance;
    private Lock lock = new ReentrantLock(); // ReentrantLock for synchronization

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        lock.lock(); // Lock for thread-safe deposit
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". Current balance: " + balance);
        } finally {
            lock.unlock(); // Always unlock in finally block to avoid deadlock
        }
    }

    public void withdraw(double amount) {
        lock.lock(); // Lock for thread-safe withdrawal
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". Current balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " attempted to withdraw " + amount + " but insufficient balance.");
            }
        } finally {
            lock.unlock(); // Always unlock in finally block to avoid deadlock
        }
    }

    public double getBalance() {
        return balance;
    }
}

class BankTransaction implements Runnable {
    private BankAccount account;
    private boolean deposit;
    private double amount;

    public BankTransaction(BankAccount account, boolean deposit, double amount) {
        this.account = account;
        this.deposit = deposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (deposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class MultiThreadedBankingApp {
    public static void main(String[] args) {
        BankAccount sharedAccount = new BankAccount(1000); // Shared resource

        // Creating threads for deposit and withdrawal
        Thread user1 = new Thread(new BankTransaction(sharedAccount, true, 200), "User 1");
        Thread user2 = new Thread(new BankTransaction(sharedAccount, false, 500), "User 2");
        Thread user3 = new Thread(new BankTransaction(sharedAccount, true, 300), "User 3");
        Thread user4 = new Thread(new BankTransaction(sharedAccount, false, 700), "User 4");

        // Start the threads
        user1.start();
        user2.start();
        user3.start();
        user4.start();

        // Wait for threads to complete
        try {
            user1.join();
            user2.join();
            user3.join();
            user4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final balance
        System.out.println("Final account balance: " + sharedAccount.getBalance());
    }
}
