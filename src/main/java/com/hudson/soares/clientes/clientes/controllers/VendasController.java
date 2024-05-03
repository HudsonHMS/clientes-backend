package com.hudson.soares.clientes.clientes.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.services.VendasService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/vendas")
@AllArgsConstructor
public class VendasController {
    
    private VendasService vendasService;

    @GetMapping("")
    public List<ClienteDTO> hello() {
        return vendasService.getClientesList();
    }

}
