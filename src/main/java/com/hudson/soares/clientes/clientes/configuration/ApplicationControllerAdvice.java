package com.hudson.soares.clientes.clientes.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hudson.soares.clientes.clientes.exceptions.ValidationMessageErros;
import com.hudson.soares.clientes.clientes.model.entities.ResponseObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestControllerAdvice
@NoArgsConstructor
@Data
public class ApplicationControllerAdvice {

    @Qualifier("validationMessages")
    private MessageSource messageSource;    
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Autowired
    public ApplicationControllerAdvice(MessageSource messageSource, HttpServletRequest request,
            HttpServletResponse response) {
        this.messageSource = messageSource;
        this.request = request;
        this.response = response;
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseObject<ValidationMessageErros> errosArgumentos( MethodArgumentNotValidException ex ) {
        final List<String> errorsList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach( err -> errorsList.add( err.getDefaultMessage() ) );
        ValidationMessageErros erros = new ValidationMessageErros(errorsList);
        return new ResponseObject<>(HttpStatus.BAD_REQUEST, erros, "Houveram erros de validação");
    }

}
