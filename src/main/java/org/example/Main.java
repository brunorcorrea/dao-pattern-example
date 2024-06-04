package org.example;

import org.example.database.DatabaseConnection;
import org.example.database.UserDAO;
import org.example.database.UserDAOImpl;
import org.example.database.UserDAOInMemory;
import org.example.entity.User;
import org.example.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            UserDAO userDAO = new UserDAOImpl();
            UserService userService = new UserService(userDAO);

            User newUser = new User(1, "John Doe", "john.doe@example.com");
            userService.createUser(newUser);

            User anotherUser = new User(2, "Aipim", "macaxeira@email.com");
            userService.createUser(anotherUser);

            User user = userService.getUser(1);
            System.out.println("User: " + user.getName() + ", " + user.getEmail());

            List<User> users = userService.listUsers();
            users.forEach(u -> System.out.println("User: " + u.getName() + ", " + u.getEmail()));

            user.setName("John Updated");
            userService.updateUser(user);

            userService.deleteUser(1);
            userService.deleteUser(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
