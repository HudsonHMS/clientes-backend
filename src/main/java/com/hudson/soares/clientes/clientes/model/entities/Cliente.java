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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "tb_clientes")
@AllArgsConstructor
@Data
public class Cliente {

    public Cliente(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    @NotBlank
    @NotNull
    @Size(min = 3, max = 255)
    private String nome;

    @Column(nullable = false, length = 11)
    @NotBlank
    @NotNull
    @Size(max = 11, min = 11)
    @CPF
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    @Valid
    private Status status;
}
