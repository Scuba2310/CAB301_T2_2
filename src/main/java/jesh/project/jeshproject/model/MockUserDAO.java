package jesh.project.jeshproject.model;

import java.util.ArrayList;
import java.util.List;

public class MockUserDAO implements IUserDAO{

    public static final ArrayList<User> users = new ArrayList<>();
    public static int autoIncrementId = 0;

    public MockUserDAO() {
        // add preset users
        addUser( new User("John", "Doe", "01/01/1992",
                "johndoe@example.com", "john_doe92", "password") );
    }

    public void addUser(User user) {
        user.setId(autoIncrementId);
        autoIncrementId++;
        users.add(user);
    }

    @Override
    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // public void updateUser(User user) { }

}