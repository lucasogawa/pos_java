package com.ogawalucas.cities.modules.cities.controller;

import com.ogawalucas.cities.modules.cities.dto.Citie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping
    public String create(Citie citie) {
        cities.add(citie);

        return "redirect:/cities";
    }
}
