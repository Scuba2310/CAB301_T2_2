package jesh.project.jeshproject.model;

import java.util.ArrayList;
import java.util.List;

public class MockUserDAO implements IUserDAO{

    public static final ArrayList<User> users = new ArrayList<>();
    public static int autoIncrementId = 0;

    public MockUserDAO() {
        // add preset users
        addUser( new User(autoIncrementId, "John", "Doe", "01/01/1992",
                "johndoe@example.com", "john_doe92", "password") );
    }

    public boolean addUser(User user) {
        users.add(user);
        if (getUser(user.getId()) == null) { return false; }
        else { return true; }
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User getUser(String identifier, UserIdentifierType identifierType) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

//    @Override
//    public User getUser(int id) {
//        for (User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        return null;
//    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return true;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void logIn(User user) {

    }

    @Override
    public void logOut() {

    }

    @Override
    public User getLoggedInUser() {
        return null;
    }

    @Override
    public Timeline getTimeline(String name, int userID) {
        return null;
    }

    @Override
    public boolean updateTimeline(Timeline timeline) {
        return false;
    }

    @Override
    public boolean addTimeline(Timeline timeline) {
        return false;
    }

    @Override
    public ArrayList<String> getTimelineNames() {
        return null;
    }

}