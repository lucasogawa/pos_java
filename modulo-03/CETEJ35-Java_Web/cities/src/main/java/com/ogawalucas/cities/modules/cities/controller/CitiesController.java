package com.ogawalucas.cities.modules.cities.controller;

import com.ogawalucas.cities.modules.cities.dto.Citie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("cities")
public class CitiesController {

    private Set<Citie> cities;

    public CitiesController() {
        cities = new HashSet<>();
    }

    @GetMapping
    public String getHtml(Model model) {
        model.addAttribute("cities", cities);

        return "/cities";
    }

    @GetMapping("model")
    public String get(Model model, @RequestParam String name, @RequestParam String state) {
            cities.stream()
                .filter(citie -> citie.getName().equals(name) && citie.getState().equals(state))
                .findFirst()
                .ifPresent(citie -> {
                    model.addAttribute("actualCitie", citie);
                    model.addAttribute("cities", cities);
                });

        return "/cities";
    }

    @PostMapping
    public String create(Model model, @Valid Citie citie, BindingResult erros) {
        if (erros.hasErrors()) {
            erros.getFieldErrors()
                .forEach(error -> model.addAttribute(error.getField(), error.getDefaultMessage()));

            model.addAttribute("informedName", citie.getName());
            model.addAttribute("informedState", citie.getState());
            model.addAttribute("cities", cities);

            return "/cities";
        }

        cities.add(citie);

        return "redirect:/cities";
    }

    @GetMapping("delete")
    public String delete(@RequestParam String name, @RequestParam String state) {
        cities.removeIf(citie -> citie.getName().equals(name) && citie.getState().equals(state));

        return "redirect:/cities";
    }

    @PostMapping("edit")
    public String edit(@RequestParam String actualName, @RequestParam String actualState, Citie citie) {
        delete(actualName, actualState);
        create(null, citie, null);

        return "redirect:/cities";
    }
}
