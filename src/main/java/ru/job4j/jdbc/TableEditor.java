package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        String driverClass = properties.getProperty("hibernate.connection.driver_class");
        String url = properties.getProperty("hibernate.connection.url");
        String username = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        Class.forName(driverClass);
        connection = DriverManager.getConnection(url, username, password);
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("create table %s();", tableName));
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("drop table %s;", tableName));
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("alter table %s add column %s %s", tableName, columnName, type));
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("alter table %s drop column %s", tableName, columnName));
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("Alter table %s rename column %s to %s;", tableName, columnName, newColumnName));
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.initConnection();
        tableEditor.createTable("Statements");
        System.out.println(tableEditor.getTableScheme("Statements"));
        tableEditor.dropTable("Statements");
        tableEditor.createTable("Statements2");
        System.out.println(tableEditor.getTableScheme("Statements2"));
        tableEditor.addColumn("Statements2", "Column1", "text");
        tableEditor.addColumn("Statements2", "Column2", "text");
        System.out.println(tableEditor.getTableScheme("Statements2"));
        tableEditor.dropColumn("Statements2", "Column1");
        System.out.println(tableEditor.getTableScheme("Statements2"));
        tableEditor.renameColumn("Statements2", "Column2", "General_column");
        System.out.println(tableEditor.getTableScheme("Statements2"));
        tableEditor.close();
    }
}