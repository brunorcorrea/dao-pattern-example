package org.example.database;

import org.example.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOInMemory implements UserDAO {

    private Map<Integer, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User getUserById(int id) {
        return users.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return users.values().stream().toList();
    }

    @Override
    public void updateUser(User user) {
        users.replace(user.getId(), user);
    }

    @Override
    public void deleteUser(int id) {
        users.remove(id);
    }
}
