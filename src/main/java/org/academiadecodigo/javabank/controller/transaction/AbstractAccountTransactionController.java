package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.AbstractController;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.Set;

public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {

    AccountService accountService;
    CustomerService customerService;

    public Set<Integer> getAccountIds() {
        return customerService.getCustomerAccountIds(authService.getAccessingCustomer().getId());
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
