package com.hudson.soares.clientes.clientes.model.interfaces.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CPFConstraint.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CPF {
    String message() default "Cpf inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};     
}
