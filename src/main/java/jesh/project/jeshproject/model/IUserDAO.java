package jesh.project.jeshproject.model;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO {

    boolean addUser(User user);

    User getUser(int id);

    User getUser(String identifier, UserIdentifierType identifierType);

    List<User> getAllUsers();

    User getUserByUsernameAndPassword(String username, String password);

    boolean updateUser(User user);
    void deleteUser(User user);
    void logIn(User user);
    void logOut();
    User getLoggedInUser();

    Timeline getTimeline(String name, int userID);

    boolean updateTimeline(Timeline timeline);

    boolean addTimeline(Timeline timeline);

    ArrayList<String> getTimelineNames();
}
