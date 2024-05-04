package com.hudson.soares.clientes.clientes.model;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hudson.soares.clientes.clientes.model.entities.Servico;
import com.hudson.soares.clientes.clientes.model.interfaces.validators.CPF;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Validated
public record ClienteDTO(Long id, @NotNull @NotBlank String nome, @NotBlank @NotNull @CPF String cpf,
                @JsonProperty(access = Access.WRITE_ONLY) @NotNull Long status_id,
                @DefaultValue(value = "Ativo") @Nullable String status_nome,
                @JsonInclude(content = Include.NON_NULL) @Valid @Nullable List<Servico> servicos) {
}
