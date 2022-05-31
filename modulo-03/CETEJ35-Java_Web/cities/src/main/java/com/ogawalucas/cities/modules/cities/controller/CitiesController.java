package com.ogawalucas.cities.modules.cities.controller;

import com.ogawalucas.cities.modules.cities.dto.CitieDto;
import com.ogawalucas.cities.modules.cities.repositories.CitieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class CitiesController {

    private Set<CitieDto> cities;
    private final CitieRepository repository;

    public CitiesController(CitieRepository repository) {
        cities = new HashSet<>();
        this.repository = repository;
    }

    @GetMapping
    public String getHtml(Model model, Principal user, HttpSession session, HttpServletResponse response) {
        model.addAttribute(
            "cities",
            repository.findAll().stream()
                .map(citie -> new CitieDto(citie.getName(), citie.getState()))
                .collect(Collectors.toList())
        );

        session.setAttribute("actualUserName", user.getName());

        response.addCookie(new Cookie("list", LocalDateTime.now().toString()));

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

    @PostMapping("create")
    public String create(Model model, @Valid CitieDto dto, BindingResult erros, HttpServletResponse response) {
        if (erros.hasErrors()) {
            erros.getFieldErrors()
                .forEach(error -> model.addAttribute(error.getField(), error.getDefaultMessage()));

            model.addAttribute("informedName", dto.getName());
            model.addAttribute("informedState", dto.getState());
            model.addAttribute("cities", cities);

            response.addCookie(new Cookie("create", LocalDateTime.now().toString()));

            return "/cities";
        }

        repository.save(dto.convertToEntity());

        return "redirect:/";
    }

    @GetMapping("delete")
    public String delete(@RequestParam String name, @RequestParam String state, HttpServletResponse response) {
        var citie = repository.findByNameAndState(name, state);

        citie.ifPresent(repository::delete);

        response.addCookie(new Cookie("delete", LocalDateTime.now().toString()));

        return "redirect:/";
    }

    @PostMapping("edit")
    public String edit(
        @RequestParam String actualName,
        @RequestParam String actualState,
        CitieDto citie,
        HttpServletResponse response
    ) {
        var actualCitie = repository.findByNameAndState(actualName, actualState);

        if (actualCitie.isPresent()) {
            actualCitie.get().setName(citie.getName());
            actualCitie.get().setState(citie.getState());
            repository.saveAndFlush(actualCitie.get());
        }

        response.addCookie(new Cookie("edit", LocalDateTime.now().toString()));

        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("show-cookie-list")
    public String showCookieList(@CookieValue String list) {
        return list;
    }
}
