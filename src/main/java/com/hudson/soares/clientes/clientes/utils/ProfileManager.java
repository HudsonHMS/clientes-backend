package com.hudson.soares.clientes.clientes.utils;


import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
public class ProfileManager {
    private Environment environment;

    public ProfileManager(Environment environment) {
        this.environment = environment;
    }

    public String getActiveProfiles() {
        return environment.getActiveProfiles()[0];
    }
}
