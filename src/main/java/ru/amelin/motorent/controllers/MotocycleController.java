package ru.amelin.motorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.amelin.motorent.dao.CustomerService;
import ru.amelin.motorent.dao.MotocycleService;
import ru.amelin.motorent.models.Customer;
import ru.amelin.motorent.models.Motocycle;
import ru.amelin.motorent.validators.MotocycleValidator;

@Controller
@RequestMapping("/moto")
public class MotocycleController {

    private final MotocycleService motocycleService;
    private final CustomerService customerService;
    private final MotocycleValidator motocycleValidator;

    @Autowired
    public MotocycleController(MotocycleService motocycleService, CustomerService customerService, MotocycleValidator motocycleValidator) {
        this.motocycleService = motocycleService;
        this.customerService = customerService;
        this.motocycleValidator = motocycleValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("motoList", motocycleService.getAll());
        return "moto/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int motoId, Model model) {
        Motocycle motocycle = motocycleService.get(motoId);
        model.addAttribute("moto", motocycle);
        model.addAttribute("renter", new Customer());
        if (motocycle.getCustomerId() != null) {
            model.addAttribute("customer", customerService.get(motocycle.getCustomerId()));
        } else {
            model.addAttribute("customers", customerService.getAll());
        }
        return "moto/show";
    }

    @GetMapping("/create")
    public String createForm(@ModelAttribute("moto") Motocycle motocycle) {
        return "moto/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("moto") Motocycle motocycle,
                         BindingResult bindingResult) {
        motocycleValidator.validate(motocycle, bindingResult);
        //todo работают только ошибки, найденные через motocycleValidator, аннотации на model классе не работают
        if (bindingResult.hasErrors()) {
            return "moto/create";
        }
        this.motocycleService.add(motocycle);
        return "redirect:/moto";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable("id") int motoId, Model model) {
        model.addAttribute("moto", motocycleService.get(motoId));
        return "moto/update";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int motoId,
                         @ModelAttribute("moto") Motocycle motocycle,
                         BindingResult bindingResult) {
        motocycleValidator.validate(motocycle, bindingResult);
        //todo работают только ошибки, найденные через motocycleValidator, аннотации на model классе не работают
        if (bindingResult.hasErrors()) {
            return "moto/update";
        }
        this.motocycleService.update(motocycle);
        return "redirect:/moto";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int motoId) {
        this.motocycleService.delete(motoId);
        return "redirect:/moto";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int motoId) {
        this.motocycleService.release(motoId);
        return "redirect:/moto/{id}";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int motoId, @ModelAttribute("renter") Customer customer) {
        this.motocycleService.assign(motoId, customer.getId());
        return "redirect:/moto/{id}";
    }
}
