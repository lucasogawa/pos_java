package com.ogawalucas.activity04client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(
    name = "sum",
    url = "${services.sum}",
    path = "/sum"
)
public interface SumClient {

    @GetMapping("{number1}/{number2}")
    int sum(@PathVariable int number1, @PathVariable int number2);
}
