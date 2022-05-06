package com.ogawalucas.cities.modules.cities.controller;

import com.ogawalucas.cities.modules.cities.dto.CitieDto;
import com.ogawalucas.cities.modules.cities.entities.Citie;
import com.ogawalucas.cities.modules.cities.repositories.CitieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("cities")
public class CitiesController {

    private Set<CitieDto> cities;
    private final CitieRepository repository;

    public CitiesController(CitieRepository repository) {
        cities = new HashSet<>();
        this.repository = repository;
    }

    @GetMapping
    public String getHtml(Model model) {
        model.addAttribute(
            "cities",
            repository.findAll().stream()
                .map(citie -> new CitieDto(citie.getName(), citie.getState()))
                .collect(Collectors.toList())
        );

        return "/cities";
    }

    @GetMapping("model")
    public String get(Model model, @RequestParam String name, @RequestParam String state) {
            repository.findByNameAndState(name, state)
                .ifPresent(citie -> {
                    model.addAttribute("actualCitie", citie);
                    model.addAttribute("cities", cities);
                });

        return "/cities";
    }

    @PostMapping
    public String create(Model model, @Valid CitieDto dto, BindingResult erros) {
        if (erros.hasErrors()) {
            erros.getFieldErrors()
                .forEach(error -> model.addAttribute(error.getField(), error.getDefaultMessage()));

            model.addAttribute("informedName", dto.getName());
            model.addAttribute("informedState", dto.getState());
            model.addAttribute("cities", cities);

            return "/cities";
        }

        repository.save(dto.convertToEntity());

        return "redirect:/cities";
    }

    @GetMapping("delete")
    public String delete(@RequestParam String name, @RequestParam String state) {
        var citie = repository.findByNameAndState(name, state);

        citie.ifPresent(repository::delete);

        return "redirect:/cities";
    }

    @PostMapping("edit")
    public String edit(@RequestParam String actualName, @RequestParam String actualState, CitieDto citie) {
        var actualCitie = repository.findByNameAndState(actualName, actualState);

        if (actualCitie.isPresent()) {
            actualCitie.get().setName(citie.getName());
            actualCitie.get().setState(citie.getState());
            repository.saveAndFlush(actualCitie.get());
        }

        return "redirect:/cities";
    }
}
