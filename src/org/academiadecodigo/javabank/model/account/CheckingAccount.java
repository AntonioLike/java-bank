package org.academiadecodigo.javabank.model.account;

public class CheckingAccount extends AbstractAccount {

    public CheckingAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
