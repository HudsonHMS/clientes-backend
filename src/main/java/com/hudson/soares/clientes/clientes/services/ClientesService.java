package com.hudson.soares.clientes.clientes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.hudson.soares.clientes.clientes.exceptions.RecordNotFoundException;
import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.model.entities.Cliente;
import com.hudson.soares.clientes.clientes.model.entities.Servico;
import com.hudson.soares.clientes.clientes.model.entities.mappers.ClientesMapper;
import com.hudson.soares.clientes.clientes.repository.ClienteJDBCRepository;
import com.hudson.soares.clientes.clientes.repository.ClientesRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Service
@Data
@Validated
public class ClientesService {

    private final ClienteJDBCRepository repository;
    private final ClientesRepository clientesRepository;

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

    public ClienteDTO getActiveClientById( Long id ) {
        Optional<Cliente> cliente = getClientesRepository().findByIdAndStatusId(id, 1);
        if( cliente.isPresent() ) {
            return ClientesMapper.tDto( cliente.get() );
        }
        return null;
    }

    public ClienteDTO updateCliente( @NotNull @Valid ClienteDTO clienteDTO ) {
        return getClientesRepository()
                .findById(clienteDTO.id())
                .map( c -> {
                    Cliente cli = ClientesMapper.toCliente(clienteDTO);

                    List<Servico> servicos = c.getServicos();

                    if( null != cli.getServicos() ) {
                        cli.getServicos().forEach( s -> {
                                Optional<Servico> serv = servicos.stream().filter(si -> si.getId() == s.getId()).findFirst();
                                if( serv.isPresent() ) {
                                    s.setDataCadastro(serv.get().getDataCadastro());
                                }
                        } );
                    }

                    if( null == cli.getServicos() || cli.getServicos().isEmpty() ) {
                        cli.setServicos(c.getServicos());
                    }

                    Cliente cliente = getClientesRepository().save( cli );
                    return ClientesMapper.tDto(cliente);
                } )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
