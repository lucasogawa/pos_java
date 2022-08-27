package com.ogawalucas.clients.modules.clients.service;

import com.ogawalucas.clients.modules.clients.client.CityClient;
import com.ogawalucas.clients.modules.clients.dto.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityClient cityClient;

    public List<City> findAll() {
        try {
            return cityClient.findAll();
        } catch (Exception ex) {
            return List.of();
        }
    }
}