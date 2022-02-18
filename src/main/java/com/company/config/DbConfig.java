package com.company.config;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public final class DbConfig {
    private static final Logger LOG = Logger.getLogger(DbConfig.class.getName());

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root123";
    private static final String URL = "jdbc:postgresql://localhost:5432/local";

    public static Connection instance() {
        return withDataSource();
    }
    public Connection getConnect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/local";
        String username = "postgres";
        String password = "root123";
        Connection c = DriverManager.getConnection(url,username,password);
        return c;
    }

    public static Connection instance(Type type) {
        switch (type) {
            case DATA_SOURCE -> {
                return withDataSource();
            }
            case DRIVER_MANAGER -> {
                return withDriverManager();
            }
            default -> {
                throw new RuntimeException("connection type is wrong");
            }
        }
    }

    private static Connection withDriverManager() {
        LOG.info("getConnectionWithDriverManager.start");
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            connection.setAutoCommit(false);
            LOG.info("getConnectionWithDriverManager.end");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't connect to database. Reason: " + e.getSQLState());
        }

    }

    private static Connection withDataSource() {
        var dataSource = new PGSimpleDataSource();
        try {
            dataSource.setDatabaseName("local");
            var conn = dataSource.getConnection(USERNAME, PASSWORD);
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }

    public enum Type {
        DRIVER_MANAGER,
        DATA_SOURCE;
    }
}
