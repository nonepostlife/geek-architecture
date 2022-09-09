package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection conn;

    private final PreparedStatement selectUser;
    private final PreparedStatement insertUser;
    private final PreparedStatement updateUser;
    private final PreparedStatement deleteUser;

    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection conn) {
        this.conn = conn;
        try {
            this.selectUser = conn.prepareStatement("select id, username, password from users where id = ?");
            this.insertUser = conn.prepareStatement("insert into users (id, username, password) values(?, ?, ?)");
            this.updateUser = conn.prepareStatement("update users set username=?, password=? where id = ?");
            this.deleteUser = conn.prepareStatement("delete from users where id = ?");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public void update(User user) {
        try {
            User exist = identityMap.get(user.getId());
            if (exist != null) {
                updateUser.setString(1, user.getUsername());
                updateUser.setString(2, user.getPassword());
                updateUser.setLong(3, user.getId());
                updateUser.execute();
                return;
            }
            selectUser.setLong(1, user.getId());
            ResultSet rs = selectUser.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException(String.format("User with id=%s not exists!", user.getId()));
            }
            updateUser.setString(1, user.getUsername());
            updateUser.setString(2, user.getPassword());
            updateUser.setLong(3, user.getId());
            updateUser.execute();

            identityMap.put(user.getId(), user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insert(User user) {
        try {
            User exist = identityMap.get(user.getId());
            if (exist != null) {
                throw new IllegalStateException(String.format("User with id=%s already exists!", user.getId()));
            }
            selectUser.setLong(1, user.getId());
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                throw new RuntimeException(String.format("User with id=%s already exists!", user.getId()));
            }
            insertUser.setLong(1, user.getId());
            insertUser.setString(2, user.getUsername());
            insertUser.setString(3, user.getPassword());
            insertUser.execute();

            identityMap.put(user.getId(), user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(User user) {
        try {
            User exist = identityMap.get(user.getId());
            if (exist != null) {
                deleteUser.setLong(1, user.getId());
                deleteUser.execute();
                return;
            }
            selectUser.setLong(1, user.getId());
            ResultSet rs = selectUser.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException(String.format("User with id=%s not exists!", user.getId()));
            }
            deleteUser.setLong(1, user.getId());
            deleteUser.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
