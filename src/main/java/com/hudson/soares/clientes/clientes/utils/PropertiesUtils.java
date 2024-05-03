package com.hudson.soares.clientes.clientes.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PropertiesUtils {

    private Properties properties;
    private ProfileManager profileManager;

    public PropertiesUtils( ProfileManager profileManager ) {
        this.profileManager = profileManager;
        this.properties =  new Properties();
        this.initiateProperties();
    }

    public Properties getProperties() {
        return this.properties;
    }

    private void initiateProperties() {
        String profileToUse = "";
        String activeProfile = getProfileManager().getActiveProfiles();
        if( null != activeProfile ) {
            profileToUse = "-" + activeProfile;
        }
        ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("application" + profileToUse +".properties");
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
