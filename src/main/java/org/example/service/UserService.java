package org.example.service;

import org.example.database.UserDAO;
import org.example.entity.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void createUser(User user) {
        userDAO.addUser(user);
    }

    public User getUser(int id) {
        return userDAO.getUserById(id);
    }

    public List<User> listUsers() {
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
