package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Controller
public class ShowCustomersController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    protected String setAboutMe (Model model) throws ServletException, IOException {

        List<Customer> customers = customerService.findAll();

        System.out.println("--------------------------------------------------------------------"+customers.get(1).getName());

        model.addAttribute("customers", customers);

        return "show-customers";
    }
}
