package ru.amelin.motorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.amelin.motorent.dao.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("customers", customerService.getAll());
        return "customers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int customerId, Model model) {
        model.addAttribute("customer", customerService.get(customerId));
        return "customers/show";
    }

}
