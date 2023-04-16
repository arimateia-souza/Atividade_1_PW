package lojainstrumento.atividade_1_pw;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    /*
    Default env:
    DATABASE_HOST=localhost;DATABASE_PORT=5432;DATABASE_NAME=tarefadb;DATABASE_USERNAME=postgres;DATABASE_PASSWORD=postgres
    */
    public static Connection getConnection() throws SQLException, URISyntaxException {
        String dbUri = "localhost";
        String dbPort = "5432";
        String dbName = "InstrumentLoja";

        String username = "postgres";
        String password = "postgres";
        String dbUrl = "jdbc:postgresql://" + dbUri + ':' + dbPort + "/" + dbName + "?serverTimezone=UTC";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}