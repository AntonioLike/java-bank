package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

public class NewAccountController extends AbstractController {

    private CustomerService customerService;
    private AccountService accountService;
    private AuthenticationService authenticationService;
    private Integer newAccountId;

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void init() {

        newAccountId = createAccount();
        super.init();
    }

    private int createAccount() {

        Account newAccount = accountService.openAccount(AccountType.CHECKING);
        customerService.addAccount(newAccount, authenticationService.getLoginCustomer().getId());

        return newAccount.getId();
    }

    public Integer getNewAccountId() {
        return newAccountId;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
