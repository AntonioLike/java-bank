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
        if(getBalance()<=amount)
            setBalance(getBalance()-amount);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract AccountType getAccountType();


    public int getId() {
        return id;
    }
}
