package com.hudson.soares.clientes.clientes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.repository.ClienteJDBCRepository;
import com.hudson.soares.clientes.clientes.repository.ClientesRepository;

import lombok.Data;

@Service
@Data
public class ClientesService {

    private ClienteJDBCRepository repository;
    private ClientesRepository clientesRepository;

    public ClientesService(ClienteJDBCRepository jdbcRepository,
            ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
        this.repository = jdbcRepository;
    }

    public List<ClienteDTO> getClientesList() {
        return this.repository.listaClientes( 1 );
    }

    public List<ClienteDTO> getClientes() {
        return this.getClientesRepository()
                   .findAll()
                   .stream()
                   .map(c -> new ClienteDTO(c.getId(), c.getNome(),
                                            c.getCpf(), c.getStatus().getId(), 
                                            c.getStatus().getNome()))
                   .toList();
    }

}
