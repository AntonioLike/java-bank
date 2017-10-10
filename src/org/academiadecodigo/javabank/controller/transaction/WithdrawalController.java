package org.academiadecodigo.javabank.controller.transaction;

public class WithdrawalController extends AbstractAccountTransactionController {

    @Override
    public void submitTransaction(int accountId, double amount) {
        bank.getAccountManager().withdraw(accountId, amount);
    }
}
