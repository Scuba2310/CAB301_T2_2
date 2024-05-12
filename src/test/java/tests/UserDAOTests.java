package tests;

import jesh.project.jeshproject.model.SqliteUserDAO;
import jesh.project.jeshproject.model.User;
import jesh.project.jeshproject.model.UserIdentifierType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDAOTests {

    private SqliteUserDAO userDAO = new SqliteUserDAO();

    @Test
    public void testGetUserById() {
        User expected = new User(1, "John", "Doe", "01/01/1992",
                "johndoe@example.com", "john_doe92", "password");
        assertEquals(expected, userDAO.getUser(1));
    }

    @Test
    public void testAddUser() {
        User expected = new User(2, "test", "test", "27/04/2024",
                "test@test.com", "tester", "ihearttests");
        userDAO.addUser(new User(3, "test", "test", "27/04/2024",
                "test@test.com", "tester", "ihearttests"));
        assertEquals(expected, userDAO.getUser(2));
    }

    @Test
    public void testGetAllUsers() {
        List<User> expected = new ArrayList<>();
        expected.add(new User(1, "John", "Doe", "01/01/1992",
                "johndoe@example.com", "john_doe92", "password"));
        expected.add(new User(2, "test", "test", "27/04/2024",
                "test@test.com", "tester", "ihearttests"));
        assertEquals(expected, userDAO.getAllUsers());
    }

    @Test
    public void testGetUserByEmail() {
        User expected = new User(1, "John", "Doe", "01/01/1992",
                "johndoe@example.com", "john_doe92", "password");
        assertEquals(expected, userDAO.getUser("johndoe@example.com", UserIdentifierType.EMAIL));
    }

    @Test
    public void testGetUserByUsername() {
        User expected = new User(1, "John", "Doe", "01/01/1992",
                "johndoe@example.com", "john_doe92", "password");
        assertEquals(expected, userDAO.getUser("john_doe92", UserIdentifierType.USERNAME));
    }
}
