package org.academiadecodigo.javabank.restcontrollers;

import org.academiadecodigo.javabank.command.CustomerForm;
import org.academiadecodigo.javabank.converters.CustomerFormToCustomer;
import org.academiadecodigo.javabank.converters.CustomerToCustomerForm;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class RestCustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    private CustomerToCustomerForm customerToCustomerForm;


    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    @ResponseBody
    public ResponseEntity<List<CustomerForm>> listCustomers() {

        return new ResponseEntity<>(customerToCustomerForm.convertList(customerService.list()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    @ResponseBody
    public String addCustomer(Model model) {
        model.addAttribute("customer", new CustomerForm());
        return "customer/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
    @ResponseBody
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerToCustomerForm.convert(customerService.get(id)));
        return "customer/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @ResponseBody
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        if ("customer" == null)
        return "customer/show";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public String saveCustomer(@Valid @RequestBody @ModelAttribute("customer") CustomerForm customerForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

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
