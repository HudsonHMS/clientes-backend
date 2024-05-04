package com.hudson.soares.clientes.clientes.model.entities;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseObject<T> {
    private HttpStatus status;
    @JsonInclude(content = Include.NON_NULL)
    private T responseData;
    @JsonInclude(value = Include.NON_DEFAULT)
    private String message;
}
