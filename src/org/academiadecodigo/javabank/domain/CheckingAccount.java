package org.academiadecodigo.javabank.domain;

public class CheckingAccount extends Account {
    public CheckingAccount(int id) {
        this.id = id;
        this.accountType = AccountType.CHECKING;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
