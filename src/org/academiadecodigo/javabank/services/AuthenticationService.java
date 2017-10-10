package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

public class AuthenticationService {

    private CustomerService customerService;
    private Customer loginCustomer;


    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean authenticateLogin(int id){
        if(customerService.getCustomerIds().contains(id)){
            loginCustomer = customerService.getCustomers().get(id);
            return true;
        }
        return false;
    }

    public Customer getLoginCustomer() {
        return loginCustomer;
    }

    public String getLoginCustomerName() {
        return loginCustomer.getName();
    }

    public int getLoginCustomerID(){
        return loginCustomer.getId();
    }
}
