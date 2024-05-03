package com.hudson.soares.clientes.clientes.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ConnectionFactory
 {
    
    private PropertiesUtils propertiesUtils;
    private Connection connection;

    public ConnectionFactory ( PropertiesUtils propertiesUtils ) {
        this.propertiesUtils = propertiesUtils;
    }

    private void generateInstance( String driver, String urlPropertie,
                    String userPropertie, String passPropertie ) throws ClassNotFoundException, SQLException {
        Class.forName(getPropertiesUtils().getProperties().getProperty(driver));
        this.connection = DriverManager.getConnection(getPropertiesUtils().getProperties().getProperty(urlPropertie), 
                                                      getPropertiesUtils().getProperties().getProperty(userPropertie), 
                                                      getPropertiesUtils().getProperties().getProperty(passPropertie));
    }

    public Connection getConnection ( String driver, String urlPropertie,
                        String userPropertie, String passPropertie ) throws ClassNotFoundException, SQLException {
        this.generateInstance(driver, urlPropertie, userPropertie, passPropertie);
        return this.connection;
    }

}
