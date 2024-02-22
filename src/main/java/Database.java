import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String URL = "jdbc:postgresql://localhost:5999/practicum";

    protected Connection getConnection() {
        try {
           return DriverManager.getConnection(URL, "postgres", "postgres");
        } catch (SQLException e ) {
            throw new RuntimeException("Не удалось подключиться к БД:" + e.getMessage());
        }
    }

    protected Connection getDatasourceConnection() {
        try {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("practicum");
            dataSource.setPortNumber(5999);
            dataSource.setUser("postgres");
            dataSource.setPassword("postgres");
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться к БД:" + e.getMessage());
        }
    }

    protected JdbcTemplate jdbcTemplate() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("practicum");
        dataSource.setPortNumber(5999);
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        return new JdbcTemplate(dataSource);
    }

    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("practicum");
        dataSource.setPortNumber(5999);
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
