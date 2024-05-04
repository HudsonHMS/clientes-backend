package com.hudson.soares.clientes.clientes.model.entities.mappers;

import java.util.List;

import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.model.entities.Cliente;
import com.hudson.soares.clientes.clientes.model.entities.Servico;
import com.hudson.soares.clientes.clientes.model.entities.Status;

public class ClientesMapper {

    private ClientesMapper() {
    }

    public static Cliente toCliente(ClienteDTO clienteDTO) {
        List<Servico> servicos = clienteDTO.servicos();
        Cliente cliente = new Cliente(clienteDTO.id(), clienteDTO.nome(), clienteDTO.cpf(),
        new Status(clienteDTO.status_id(), "Ativo"), servicos);
        if( null != servicos ) {
            servicos.forEach(s -> s.setCliente(cliente));
        }
        return cliente;
    }

    public static ClienteDTO tDto(Cliente cliente) {

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getStatus().getId(),
                cliente.getStatus().getNome(), cliente.getServicos());
    }

}
