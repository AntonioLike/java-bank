package org.academiadecodigo.javabank.dto.conversors;

import org.academiadecodigo.javabank.dto.CustomerDTO;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOConversor {

    public Customer dtoToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setEmail(customerDTO.getEmail());
        customer.setFirstName(customerDTO.getEmail());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhone());

        return customer;
    }

    public CustomerDTO customerToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setFirstName(customer.getEmail());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPhone(customer.getPhone());

        return customerDTO;
    }

}
