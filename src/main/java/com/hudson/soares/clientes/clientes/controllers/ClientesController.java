package com.hudson.soares.clientes.clientes.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hudson.soares.clientes.clientes.exceptions.RecordNotFoundException;
import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.model.entities.ResponseObject;
import com.hudson.soares.clientes.clientes.services.ClientesService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
@Validated
@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@Data
public class ClientesController {
    
    private ClientesService vendasService;

    @GetMapping("")
    public ResponseObject<List<ClienteDTO>> index() {
        return new ResponseObject<>(HttpStatus.OK, getVendasService().getClientes(), null);
    }

    @PostMapping("/add")
    public ResponseObject<ClienteDTO> add( @RequestBody @Valid ClienteDTO clienteDTO ) {
        return new ResponseObject<>(HttpStatus.CREATED, getVendasService().add(clienteDTO), "Cliente cadastrado com sucesso");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<Object> deleteClienteById( @PathVariable Long id, HttpServletResponse response ) {
        try {
            response.setStatus(200);
            getVendasService().delete(id);
            return new ResponseObject<>(HttpStatus.OK, null, "Cliente deletado com sucesso");
        } catch( RecordNotFoundException e ) {
            response.setStatus(400);
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, null, e.getMessage());
        }
    }

}
