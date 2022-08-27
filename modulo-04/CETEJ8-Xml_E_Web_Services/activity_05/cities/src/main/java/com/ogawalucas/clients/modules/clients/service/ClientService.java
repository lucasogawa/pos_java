package com.ogawalucas.clients.modules.clients.service;

import com.ogawalucas.clients.modules.clients.dto.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClientService {
    private Set<Client> clients = new HashSet<>();

    @Autowired
    private CityService cityService;

    public void getHtml(Model model) {
        model.addAttribute("clients", findAll());
        model.addAttribute("cities", cityService.findAll());
    }

    private Set<Client> findAll() {
        return clients;
    }

    public void findByCode(Model model, int code) {
        clients.stream()
            .filter(client -> client.code() == code)
            .findFirst()
            .ifPresent(client -> {
                model.addAttribute("client", client);
                model.addAttribute("clients", findAll());
                model.addAttribute("cities", cityService.findAll());
            });
    }

    public void create(Client client) {
        clients.add(client);
    }

    public void deleteByCode(int code) {
        clients.removeIf(client -> client.code() == code);
    }

    public void update(Client client) {
        deleteByCode(client.code());
        create(client);
    }
}
