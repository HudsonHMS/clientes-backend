package com.hudson.soares.clientes.clientes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hudson.soares.clientes.clientes.model.entities.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByStatusIn( Integer[] status );
    Optional<Cliente> findByIdAndStatusId( Long id, Integer status );
}
