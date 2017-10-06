package org.academiadecodigo.javabank.test;


import org.academiadecodigo.javabank.domain.Customer;

public class InterfaceTest {
    public static void main(String[] args) {
        Customer customer = new Customer();
        InterfaceExistingClient interfaceExistingClient = new InterfaceExistingClient(customer);
        int userInput = interfaceExistingClient.getUserInput();

    }
}
