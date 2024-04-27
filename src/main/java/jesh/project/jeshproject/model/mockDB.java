package jesh.project.jeshproject.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class mockDB {

    public static final ArrayList<User> users = new ArrayList<>();
    public static int autoIncrementId = 0;

    public mockDB() {
        // implement user class
        // add users
        addUser( new User("test", "test", "27/04/2024", "test@test.com", "tester", "ihearttests") );
        addUser( new User("John", "Doe", "01/01/1992", "johndoe@example.com", "john_doe92", "password") );
    }

    public void getUsers() {

    }
    public static void addUser(User user) {
        user.setId(autoIncrementId);
        autoIncrementId++;
        users.add(user);
    }

    // public void updateUser(User user) { }

}
