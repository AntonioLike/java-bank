package org.academiadecodigo.javabank.converters;

import org.academiadecodigo.javabank.command.CustomerForm;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {

    @Autowired
    CustomerService customerService;

    @Override
    public Customer convert(CustomerForm customerForm) {

        Customer customer = (customerForm.getId() != null ? customerService.get(customerForm.getId()) : new Customer());

        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhone(customerForm.getPhone());

        return customer;
    }
}
