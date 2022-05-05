package com.ogawalucas.cities.modules.cities.controller;

import com.ogawalucas.cities.modules.cities.dto.Citie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
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
}
