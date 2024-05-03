package com.hudson.soares.clientes.clientes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hudson.soares.clientes.clientes.model.interfaces.Animal;

@Configuration
public class AnimalConfiguration {
    @Bean(name = "cachorro")
    public Animal cachorro() {
        return () -> System.out.println("Au au au!!");
    }

    @Bean(name = "gato")
    public Animal gato() {
        return () -> System.out.println("Miau miau!!");
    }
}
