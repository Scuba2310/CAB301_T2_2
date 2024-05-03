package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import jesh.project.jeshproject.model.MockUserDAO;
import jesh.project.jeshproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTests {

    private MockUserDAO userDAO = new MockUserDAO();

    @Test
    public void testGetUser() {
        User expected = new User("John", "Doe", "01/01/1992",
                "johndoe@example.com", "john_doe92", "password");
        assertEquals(expected, userDAO.getUser(0));
    }

    @Test
    public void testAddUser() {
        User expected = new User("test", "test", "27/04/2024",
                "test@test.com", "tester", "ihearttests");
        userDAO.addUser(new User("test", "test", "27/04/2024",
                "test@test.com", "tester", "ihearttests"));
        assertEquals(expected, userDAO.getUser(1));
    }

    @Test
    public void testGetAllUsers() {
        List<User> expected = new ArrayList<User>();
        expected.add(userDAO.getUser(0));
        expected.add(userDAO.getUser(1));
        assertEquals(expected, userDAO.getAllUsers());
    }
}
