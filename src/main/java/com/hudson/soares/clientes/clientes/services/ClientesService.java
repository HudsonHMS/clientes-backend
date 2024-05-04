package com.hudson.soares.clientes.clientes.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.model.entities.mappers.ClientesMapper;
import com.hudson.soares.clientes.clientes.repository.ClienteJDBCRepository;
import com.hudson.soares.clientes.clientes.repository.ClientesRepository;

import jakarta.validation.Valid;
import lombok.Data;

@Service
@Data
@Validated
public class ClientesService {

    private ClienteJDBCRepository repository;
    private ClientesRepository clientesRepository;

    public ClientesService(ClienteJDBCRepository jdbcRepository,
            ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
        this.repository = jdbcRepository;
    }

    public List<ClienteDTO> getClientesList() {
        return this.repository.listaClientesAtivos(1L);
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

    public ClienteDTO add( @Valid ClienteDTO clienteDTO) {
        return ClientesMapper.tDto(getClientesRepository().save(ClientesMapper.toCliente(clienteDTO)));
    }

}
