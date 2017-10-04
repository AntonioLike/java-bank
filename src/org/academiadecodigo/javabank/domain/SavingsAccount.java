package org.academiadecodigo.javabank.domain;

public class SavingsAccount extends Account {
    public SavingsAccount(int id) {
        this.id = id;
        this.accountType = AccountType.SAVINGS;
    }

    @Override
    public void debit(double amount) {
        if(this.getBalance()>=amount+Customer.MIN_SAVINGS_BALANCE)
            this.setBalance(this.getBalance()-amount);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }
}
