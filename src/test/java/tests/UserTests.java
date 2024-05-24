package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import jesh.project.jeshproject.model.User;

public class UserTests {

    private User user;
    @BeforeEach
    public void setup() {
        user = new User(0, "John", "Doe", "01/01/1992",
                "johndoe@example.com", "john_doe92", "password");
        //user.setId(0); // usually set by the UserDAO
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", user.getFirstName());
    }
    @Test
    public void testSetFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", user.getLastName());
    }
    @Test
    public void testSetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testGetBirthday() {
        assertEquals("01/01/1992", user.getBirthday());
    }
    @Test
    public void testSetBirthday() {
        user.setBirthday("23/07/1998");
        assertEquals("23/07/1998", user.getBirthday());
    }

    @Test
    public void testGetUsername() {
        assertEquals("john_doe92", user.getUsername());
    }
    @Test
    public void testSetUsername() {
        user.setUsername("SMane_98");
        assertEquals("SMane_98", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", user.getPassword());
    }
    @Test
    public void testSetPassword() {
        user.setPassword("Password123");
        assertEquals("Password123", user.getPassword());
    }

    @Test
    public void testGetId() {
        assertEquals(0, user.getId());
    }
    @Test
    public void testSetId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

}