import org.example.database.UserDAOInMemory;
import org.example.entity.User;
import org.example.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserServiceWithInMemoryDatabaseTest {

    private static final UserService userService = new UserService(new UserDAOInMemory());

    @Test
    public void testCreateUser() {
        User newUser = new User(1, "Jane Doe", "jane.doe@example.com");
        userService.createUser(newUser);

        User userFromDb = userService.getUser(1);
        Assert.assertNotNull(userFromDb);
        Assert.assertEquals("Jane Doe", userFromDb.getName());
        Assert.assertEquals("jane.doe@example.com", userFromDb.getEmail());
    }

    @Test
    public void testGetUser() {
        User newUser = new User(2, "Alice", "alice@example.com");
        userService.createUser(newUser);

        User userFromDb = userService.getUser(2);

        Assert.assertNotNull(userFromDb);
        Assert.assertEquals("Alice", userFromDb.getName());
        Assert.assertEquals("alice@example.com", userFromDb.getEmail());
    }

    @Test
    public void testUpdateUser() {
        User newUser = new User(3, "Bob", "bob@example.com");
        userService.createUser(newUser);

        newUser.setName("Bob Updated");
        newUser.setEmail("bob.updated@example.com");
        userService.updateUser(newUser);

        User userFromDb = userService.getUser(3);
        Assert.assertEquals("Bob Updated", userFromDb.getName());
        Assert.assertEquals("bob.updated@example.com", userFromDb.getEmail());
    }

    @Test
    public void testDeleteUser() {
        User newUser = new User(4, "Charlie", "charlie@example.com");
        userService.createUser(newUser);

        userService.deleteUser(4);

        User userFromDb = userService.getUser(4);
        Assert.assertNull(userFromDb);
    }

    @Test
    public void testListUsers() {
        User user1 = new User(5, "Dave", "dave@example.com");
        User user2 = new User(6, "Eve", "eve@example.com");
        userService.createUser(user1);
        userService.createUser(user2);

        List<User> users = userService.listUsers();
        Assert.assertTrue(users.size() >= 2);
    }
}
