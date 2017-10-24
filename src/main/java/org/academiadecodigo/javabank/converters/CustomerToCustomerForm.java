package org.academiadecodigo.javabank.converters;

import org.academiadecodigo.javabank.command.CustomerForm;
import org.academiadecodigo.javabank.model.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {

    @Override
    public CustomerForm convert(Customer customer) {

        CustomerForm customerForm = new CustomerForm();
        customerForm.setId(customer.getId());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setEmail(customer.getEmail());
        customerForm.setPhone(customer.getPhone());

        return customerForm;
    }

    public List<CustomerForm> convertList(List<Customer> customerList) {

        List<CustomerForm> customerFormList = new ArrayList<>();

        for (Customer customer : customerList)
            customerFormList.add(convert(customerList.get(customer.getId())));

        return customerFormList;

    }
}
