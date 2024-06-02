package org.example;

import org.example.database.DatabaseConnection;
import org.example.database.UserDAO;
import org.example.database.UserDAOImpl;
import org.example.entity.User;
import org.example.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAOImpl(connection);
            UserService userService = new UserService(userDAO);

            // Cria um novo usuário
            User newUser = new User(1, "John Doe", "john.doe@example.com");
            userService.createUser(newUser);

            // Obtém um usuário pelo ID
            User user = userService.getUser(1);
            System.out.println("User: " + user.getName() + ", " + user.getEmail());

            // Lista todos os usuários
            List<User> users = userService.listUsers();
            users.forEach(u -> System.out.println("User: " + u.getName() + ", " + u.getEmail()));

            // Atualiza um usuário
            user.setName("John Updated");
            userService.updateUser(user);

            // Deleta um usuário
            userService.deleteUser(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
