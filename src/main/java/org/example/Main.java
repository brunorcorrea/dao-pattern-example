package org.example;

import org.example.database.DatabaseConnection;
import org.example.database.UserDAO;
import org.example.database.UserDAOImpl;
import org.example.entity.User;
import org.example.service.UserService;

import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.getConnection()) {

            UserDAO userDAO = new UserDAOImpl(connection);
            UserService userService = new UserService(userDAO);

            User newUser = new User(1, "John Doe", "john.doe@example.com");
            System.out.println("-- Creating user: " + newUser.getName() + " --");
            userService.createUser(newUser);
            System.out.println("-- User: " + newUser.getName() + " created successfully --\n");

            User anotherUser = new User(2, "Aipim", "macaxeira@email.com");
            System.out.println("-- Creating user: " + anotherUser.getName() + " --");
            userService.createUser(anotherUser);
            System.out.println("-- User: " + anotherUser.getName() + " created successfully --\n");

            System.out.println("-- Viewing user details --");
            User user = userService.getUser(1);
            System.out.println("User: " + user.getName() + ", " + user.getEmail());
            System.out.println("----\n");

            System.out.println("-- Listing users --");
            List<User> users = userService.listUsers();
            users.forEach(u -> System.out.println("User: " + u.getName() + ", " + u.getEmail()));
            System.out.println("----\n");

            user.setName("John Updated");
            userService.updateUser(user);

            System.out.println("-- Deleting user: " + newUser.getName() + " --");
            userService.deleteUser(1);
            System.out.println("-- User: " + newUser.getName() + " deleted successfully --\n");

            System.out.println("-- Deleting user: " + anotherUser.getName() + " --");
            userService.deleteUser(2);
            System.out.println("-- User: " + anotherUser.getName() + " deleted successfully --\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
