package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementDemo {

    private Connection connection;

    public PreparedStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("Update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("Select * from cities")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("population")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public City insert(City city) {
        try (PreparedStatement statement = connection.prepareStatement("Insert into cities(name, population) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

}