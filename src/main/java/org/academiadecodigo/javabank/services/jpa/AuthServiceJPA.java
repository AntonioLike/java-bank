package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.CustomerService;

import javax.persistence.EntityManagerFactory;

public class AuthServiceJPA implements AuthService {

    private EntityManagerFactory emf;
    private Customer accesingCustomer;
    private CustomerService customerService;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Customer getAccesingCustomer() {
        return accesingCustomer;
    }

    public void setAccesingCustomer(Customer accesingCustomer) {
        this.accesingCustomer = accesingCustomer;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public AuthServiceJPA(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean authenticate(Integer id) {
        accesingCustomer = customerService.findById(id);
        return accesingCustomer != null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return accesingCustomer;
    }


}
