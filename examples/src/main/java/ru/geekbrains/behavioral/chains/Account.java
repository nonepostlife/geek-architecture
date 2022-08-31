package ru.geekbrains.behavioral.chains;

public abstract class Account {
    private Account successor;
    private final int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public void setNext(Account successor) {
        this.successor = successor;
    }

    public void pay(int amountToPay) {
        if (canPay(amountToPay)) {
            System.out.printf("Paid %s using %s\n", amountToPay, this.getClass().getName());
        } else if (successor != null) {
            System.out.printf("Cannot pay using %s. Proceeding ..\n", this.getClass().getName());
            successor.pay(amountToPay);
        } else {
            throw new IllegalStateException("None of the accounts have enough balance\n");
        }
    }

    public boolean canPay(int amount) {
        return this.balance >= amount;
    }
}
