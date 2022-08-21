package com.ogawalucas.activity04service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sum")
public class SumController {

    @GetMapping("{number1}/{number2}")
    public int sum(@PathVariable int number1, @PathVariable int number2) {
        return number1 + number2;
    }
}
