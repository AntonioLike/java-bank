package org.academiadecodigo.javabank.services.mock;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.CustomerService;

public class AuthServiceImpl implements AuthService {


    private Customer accessingCustomer;
    private CustomerService customerService;

    @Override
    public boolean authenticate(Integer id) {

        accessingCustomer = customerService.findById(id);
        return accessingCustomer != null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return accessingCustomer;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
