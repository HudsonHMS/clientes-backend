package com.hudson.soares.clientes.clientes.model;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hudson.soares.clientes.clientes.model.interfaces.validators.CPF;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Validated
public record ClienteDTO(Long id, @NotNull @NotBlank String nome, @NotBlank @NotNull @CPF String cpf,
        @JsonProperty(access = Access.WRITE_ONLY) @NotNull Long status_id, @DefaultValue(value = "Ativo") @Nullable String status_nome) {
}
