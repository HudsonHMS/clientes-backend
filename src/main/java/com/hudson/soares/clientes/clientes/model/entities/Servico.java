package com.hudson.soares.clientes.clientes.model.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_servico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servico {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 255, nullable = false)
    private String descricao;

    @Column
    @NotNull
    @Positive
    private Double valor;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT NOW()", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape=JsonFormat.Shape.STRING, locale = "default")
    @JsonProperty(value = "data_cadastro")
    private LocalDateTime dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(referencedColumnName = "id", nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Cliente cliente;

    @PrePersist
    public void beforeSave() {
        if( null == getDataCadastro() ){
            setDataCadastro(LocalDateTime.now());
        }
    }

}
