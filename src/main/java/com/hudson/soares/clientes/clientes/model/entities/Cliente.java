package com.hudson.soares.clientes.clientes.model.entities;

import com.hudson.soares.clientes.clientes.model.interfaces.validators.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "tb_clientes")
@AllArgsConstructor
@Data
@Transactional
public class Cliente {

    public Cliente(){ /** default contructor */ }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    @Size(max = 255, min = 3)
    @NotBlank
    @NotNull
    private String nome;

    @Column(length = 11, nullable = false)
    @Size(max = 11, min = 11)
    @NotBlank
    @NotNull
    @CPF
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(referencedColumnName = "id")
    @NotNull
    private Status status;
}
