package jesh.project.jeshproject.model;

import java.util.ArrayList;
import java.util.List;
import jesh.project.jeshproject.model.User;

public class mockDB implements IUserDAO{

    public static final ArrayList<User> users = new ArrayList<>();
    public static int autoIncrementId = 0;

    public mockDB() {
        // implement user class
        // add users
        addUser( new User("test", "test", "27/04/2024", "test@test.com", "tester", "ihearttests") );
        addUser( new User("John", "Doe", "01/01/1992", "johndoe@example.com", "john_doe92", "password") );
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