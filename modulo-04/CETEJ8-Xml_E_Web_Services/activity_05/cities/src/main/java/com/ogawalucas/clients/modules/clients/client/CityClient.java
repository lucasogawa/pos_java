package com.ogawalucas.clients.modules.clients.client;

import com.ogawalucas.clients.modules.clients.dto.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient(
    name = "city",
    url = "${services.city}",
    path = "/cidade"
)
public interface CityClient {

    @GetMapping
    List<City> findAll();
}
