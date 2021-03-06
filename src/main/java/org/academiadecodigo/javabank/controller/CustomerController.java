package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.command.CustomerForm;
import org.academiadecodigo.javabank.converters.CustomerFormToCustomer;
import org.academiadecodigo.javabank.converters.CustomerToCustomerForm;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    private CustomerToCustomerForm customerToCustomerForm;

    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.list());
        return "customer/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new CustomerForm());
        return "customer/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerToCustomerForm.convert(customerService.get(id)));
        return "customer/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        return "customer/show";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerForm customerForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
           return "customer/add-update";
        }

        Customer savedCustomer = customerService.save(customerFormToCustomer.convert(customerForm));

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedCustomer.getFirstName() + " " + savedCustomer.getLastName());
        return "redirect:/customer/";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Customer customer = customerService.get(id);
        customerService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + customer.getFirstName() + " " + customer.getLastName());
        return "redirect:/customer";
    }

    @ExceptionHandler(Exception.class)
    public void handleAllException(Exception ex) {
        ex.printStackTrace();
    }
}
