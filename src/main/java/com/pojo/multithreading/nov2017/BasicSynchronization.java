package com.pojo.multithreading.nov2017;

/**
 * Created by sunitc on 11/17/17.
 */
public class BasicSynchronization {

    public static class BankAccount {
        private int balance;
        public BankAccount(int balance) {
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        //Thread safe method to add balance
        public void addBalance(int amount) {
            synchronized (this) {
                this.balance = this.balance + amount;
            }
        }

        //Non-thread safe method for add balance
        public void addBalanceRisky(int amount) {
            this.balance = this.balance + amount;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        runWithoutSynchronization();
        runWithSynchronization();

    }

    private static void runWithSynchronization() throws InterruptedException {
        //Create three bank accounts all with initial balance of 10K
        BankAccount ba = new BankAccount(10000);
        BankAccount bb = new BankAccount(10000);
        BankAccount bc = new BankAccount(10000);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ba.addBalance(-10);
                bb.addBalance(5);
                bc.addBalance(5);
            }
        };

        for (int i = 1; i <= 1000; i++) {
            new Thread(runnable).start();
        }

        Thread.sleep(2000);
        System.out.println("Thread Safe: Account A = " + ba.getBalance() + " , Account B = " + bb.getBalance() + " , Account C =" + bc.getBalance());
    }

    private static void runWithoutSynchronization() throws InterruptedException {
        //Create three bank accounts all with initial balance of 10K
        BankAccount ba = new BankAccount(10000);
        BankAccount bb = new BankAccount(10000);
        BankAccount bc = new BankAccount(10000);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ba.addBalanceRisky(-10);
                bb.addBalanceRisky(5);
                bc.addBalanceRisky(5);
            }
        };

        for (int i = 1; i <= 1000; i++) {
            new Thread(runnable).start();
        }

        Thread.sleep(2000);
        System.out.println("Non Thread Safe: Account A = " + ba.getBalance() + " , Account B = " + bb.getBalance() + " , Account C =" + bc.getBalance());
    }
}
