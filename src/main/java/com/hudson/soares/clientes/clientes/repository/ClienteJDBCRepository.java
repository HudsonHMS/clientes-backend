package com.hudson.soares.clientes.clientes.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import com.hudson.soares.clientes.clientes.model.ClienteDTO;
import com.hudson.soares.clientes.clientes.utils.ConnectionFactory;

import lombok.Data;

@Component
@Data
public class ClienteJDBCRepository implements DisposableBean {

    private Connection localConnection;
    private ConnectionFactory connectionFactory;

    public ClienteJDBCRepository( ConnectionFactory connectionFactory ) {
        this.connectionFactory = connectionFactory;
          try {
            setLocalConnection(getConnectionFactory().getConnection("spring.datasource.driver-class-name", 
                                                       "spring.datasource.url",
                                                      "spring.datasource.username", 
                                                      "spring.datasource.password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ClienteDTO> listaClientesAtivos( Long status ) {
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
            preparedStatement = this.localConnection.prepareStatement(sql);
            preparedStatement.setLong(1, status);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new ClienteDTO(resultSet.getLong("id"), resultSet.getString("nome"),
                        resultSet.getString("cpf"), resultSet.getLong("status"), resultSet.getString("status_nome")));
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
        this.localConnection.close();
    }

}
