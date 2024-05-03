package com.hudson.soares.clientes.clientes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.repository.JDBCRepository;

@Service
public class VendasService {

    private JDBCRepository repository;

    public VendasService( JDBCRepository jdbcRepository ) {
        this.repository = jdbcRepository;
    }

    public List<ClienteDTO> getClientesList() {
        return this.repository.listaClientes( 1 );
    }

}
