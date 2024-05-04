package com.hudson.soares.clientes.clientes.model.entities.mappers;

import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.model.entities.Cliente;
import com.hudson.soares.clientes.clientes.model.entities.Status;

public class ClientesMapper {

    private ClientesMapper () {}

    public static Cliente toCliente(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.id(), clienteDTO.nome(), clienteDTO.cpf(), new Status(clienteDTO.status_id(), "Ativo"));
    }

    public static ClienteDTO tDto(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getStatus().getId(),
                cliente.getStatus().getNome());
    }

}
