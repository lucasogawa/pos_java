package com.ogawalucas.cities.modules.cities.controller;

import com.ogawalucas.cities.modules.cities.dto.Citie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CitiesController {
    @GetMapping
    public String getHtml(Model model) {
        var cities = List.of(
            new Citie("Assis", "SP"),
            new Citie("Cornélio Procópio", "PR"),
            new Citie("Itajaí", "SC")
        );



        model.addAttribute("cities", cities);

        return "/cities";
    }
}
