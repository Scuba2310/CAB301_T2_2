package jesh.project.jeshproject.model;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public interface IUserDAO {
    boolean addUser(User user) throws SQLException;
    User getUser(int id);
    User getUser(String identifier, UserIdentifierType identifierType);
    User getUserByUsernameAndPassword(String username, String password);
    boolean updateUser(User user);
    void deleteUser(User user);
    void logIn(User user);
    void logOut();
    User getLoggedInUser();
    List<User> getAllUsers();

    boolean addTimeline(Timeline timeline);
    Timeline getTimeline(String name, int userID);
    ArrayList<String> getTimelineNames(int userID);
    boolean updateTimeline(Timeline timeline);
    void deleteTimeline(int timelineId);

    Timeline getTimelineByID(int id);
}
