package com.ogawalucas.cities.modules.cities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CitiesController {

    @GetMapping
    public String getHtml() {
        return "cities.html";
    }
}
