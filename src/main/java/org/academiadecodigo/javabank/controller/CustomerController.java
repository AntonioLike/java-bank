package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.dto.CustomerDTO;
import org.academiadecodigo.javabank.dto.conversors.CustomerDTOConversor;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerDTOConversor customerConversor;

    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.list());
        return "/customer/list";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        return "/customer/show";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String createCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/add-or-edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addcustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors())
        {
            return "customer/add-or-edit";
        }

        Customer customer = customerConversor.dtoToCustomer(customerDTO);

        Customer savedCustomer = customerService.save(customer);
        redirectAttributes.addFlashAttribute("lastAction", "Added customer successfully!");
        // Instead of returning a rendered view to the browser,
        // a 302 redirect is sent to the browser, forcing showCustomer()
        // to execute after adding a new user
        return "redirect:/customer/" + savedCustomer.getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute( "customer", customerService.get(id));
        return "customer/add-or-edit";
    }
}
