package ru.amelin.motorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.amelin.motorent.dao.MotocycleService;
import ru.amelin.motorent.models.Motocycle;

@Controller
@RequestMapping("/moto")
public class MotocycleController {

    private MotocycleService motocycleService;

    @Autowired
    public MotocycleController(MotocycleService motocycleService) {
        this.motocycleService = motocycleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("motoList", motocycleService.getAll());
        return "moto/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int motoId, Model model) {
        model.addAttribute("moto", motocycleService.get(motoId));
        return "moto/show";
    }

    @GetMapping("/create")
    public String createForm(@ModelAttribute("moto") Motocycle motocycle) {
        return "moto/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("moto") Motocycle motocycle,
                         BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);

//        if (bindingResult.hasErrors()) {
//            return "people/new";
//        }
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
//        personValidator.validate(person, bindingResult);

//        if (bindingResult.hasErrors()) {
//            return "people/new";
//        }
        this.motocycleService.update(motocycle);
        return "redirect:/moto";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int motoId) {
        this.motocycleService.delete(motoId);
        return "redirect:/moto";
    }
}
