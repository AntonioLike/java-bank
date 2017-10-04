package org.academiadecodigo.javabank.domain;

public abstract class Account {

    protected AccountType accountType;
    private double balance = 0;
    protected int id;

    /*public Account(int id, AccountType accountType) {
        this.id = id;
        this.accountType = accountType;
    }*/

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }


    public int getId() {
        return id;
    }
}
