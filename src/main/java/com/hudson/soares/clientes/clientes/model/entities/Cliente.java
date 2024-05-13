package com.hudson.soares.clientes.clientes.model.entities;

import java.util.List;

import com.hudson.soares.clientes.clientes.model.interfaces.validators.CPF;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_clientes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Transactional
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    @Size(max = 255, min = 3, message = "{validations.nome.size}")
    @NotBlank(message = "{validations.nome.blank}")
    @NotNull(message = "{validations.nome}")
    private String nome;

    @Column(length = 11, nullable = false)
    @Size(max = 11, min = 11)
    @NotBlank(message = "{validations.cpf.blank}")
    @NotNull(message = "{validations.cpf.null}")
    @CPF(message = "{validations.cpf}")
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    @NotNull
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente", orphanRemoval = true)
    @Nullable
    private List<Servico> servicos;
}
