package com.ogawalucas.clients.modules.clients.controller;

import com.ogawalucas.clients.modules.clients.dto.Client;
import com.ogawalucas.clients.modules.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public String getHtml(Model model) {
        service.getHtml(model);

        return "clients";
    }

    @GetMapping("model")
    public String findByCode(Model model, @RequestParam int code) {
        service.findByCode(model, code);

        return "clients";
    }

    @PostMapping
    public String create(Client client) {
        service.create(client);

        return "redirect:/clients";
    }

    @GetMapping("delete")
    public String deleteByCode(@RequestParam int code) {
        service.deleteByCode(code);

        return "redirect:/clients";
    }

    @PostMapping("update")
    public String update(@RequestParam int actualCode, Client client) {
        service.update(actualCode, client);

        return "redirect:/clients";
    }
}
