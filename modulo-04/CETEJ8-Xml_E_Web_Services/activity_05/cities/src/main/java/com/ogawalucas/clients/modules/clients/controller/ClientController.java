package com.ogawalucas.clients.modules.clients.controller;

import com.ogawalucas.clients.modules.clients.dto.Client;
import com.ogawalucas.clients.modules.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("html")
    public String getHtml(Model model) {
        service.getHtml(model);

        return "clients";
    }

    @PostMapping
    public String create(Client client) {
        service.create(client);

        return "redirect:/clients/html";
    }

    @GetMapping("{code}")
    public String findByCode(Model model, @RequestParam int code) {
        service.findByCode(model, code);

        return "redirect:/clients/html";
    }

    @PutMapping
    public String update(Client client) {
        service.update(client);

        return "redirect:/clients/html";
    }

    @DeleteMapping("{code}")
    public String deleteByCode(@RequestParam int code) {
        service.deleteByCode(code);

        return "redirect:/clients/html";
    }
}
