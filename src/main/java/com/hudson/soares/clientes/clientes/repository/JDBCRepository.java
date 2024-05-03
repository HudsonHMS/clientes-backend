package com.hudson.soares.clientes.clientes.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.utils.PropertiesUtils;

import lombok.Data;

@Component
@Data
public class JDBCRepository implements DisposableBean {

    private Connection connection;

    private String urlConnection;
    private String passwd;
    private String user;

    private PropertiesUtils propertiesUtils;

    public JDBCRepository( PropertiesUtils propertiesUtils ) {
        this.propertiesUtils = propertiesUtils;
        try {
            this.urlConnection = getPropertiesUtils().getProperties().getProperty("spring.datasource.url");
            this.passwd = getPropertiesUtils().getProperties().getProperty("spring.datasource.password");
            this.user   = getPropertiesUtils().getProperties().getProperty("spring.datasource.username");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(urlConnection, user, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ClienteDTO> listaClientes( Integer status ) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            final String sql = """
                         Select
                         cli.id as id
                        ,cli.nome as nome
                        ,cli.cpf as cpf
                        ,cli.status_id as status
                        ,sts.nome  as status_nome
                    from
                        tb_clientes cli
                            inner join tb_status sts
                        on sts.id = cli.status_id
                    where status_id = ?
                    order by nome;
                             """;
            List<ClienteDTO> list = new ArrayList<>();
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, status);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new ClienteDTO(resultSet.getInt("id"), resultSet.getString("nome"),
                        resultSet.getString("cpf"), resultSet.getInt("status"), resultSet.getString("status_nome")));
            }
            resultSet.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if( null != preparedStatement ) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void destroy() throws Exception {
        this.connection.close();
    }

}
