package com.hudson.soares.clientes.clientes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.hudson.soares.clientes.clientes.exceptions.RecordNotFoundException;
import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.model.entities.Cliente;
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
                        c.getStatus().getNome(), c.getServicos()))
                .toList();
    }

    public ClienteDTO add( @Valid ClienteDTO clienteDTO) {
        return ClientesMapper.tDto(getClientesRepository().save(ClientesMapper.toCliente(clienteDTO)));
    }

    public boolean delete( Long id ) throws RecordNotFoundException {

        Optional<Cliente> cliente = getClientesRepository().findById(id);

        if( !cliente.isPresent() ) {
            throw new RecordNotFoundException("Registro com o id " + id + " não encontrado");
        }

        getClientesRepository().deleteById(id);

        return true;
    }

}
