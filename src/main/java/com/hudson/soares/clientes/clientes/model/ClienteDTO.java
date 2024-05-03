package com.hudson.soares.clientes.clientes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDTO(@NotNull Long id, @NotNull @NotBlank String nome, @NotBlank String cpf,
        @Nullable @JsonIgnore Long status_id, @Nullable String status_nome) {
}
