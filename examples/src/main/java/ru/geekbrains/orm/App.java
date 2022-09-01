package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/orm?useSSL=false";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            UserMapper userMapper = new UserMapper(connection);

            long id = 1;

            User user = new User(id, "John", "pass3");
            userMapper.insert(user);

            User byId = userMapper.findById(id).orElseThrow(() -> new RuntimeException(String.format("User by id=%s not found", id)));
            System.out.println(byId.getUsername());
            byId.setUsername("Mary");
            userMapper.update(user);

            byId = userMapper.findById(id).orElseThrow(() -> new RuntimeException(String.format("User by id=%s not found", id)));
            System.out.println(byId.getUsername());
            userMapper.delete(byId);


            UserRepository repository = new UserRepository(connection);
            repository.beginTransaction();
            repository.insert(user);
            byId.setUsername("Sam");
            repository.update(user);
            repository.commitTransaction();
            repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("User by id=%s not found", id)));
            System.out.println(byId.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
