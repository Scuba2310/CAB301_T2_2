package jesh.project.jeshproject.model;

import java.util.List;

public interface IUserDAO {

    boolean addUser(User user);

    User getUser(int id);

    User getUser(String identifier, UserIdentifierType identifierType);

    List<User> getAllUsers();

    User getUserByUsernameAndPassword(String username, String password);

    void updateUser(User user);
    void deleteUser(User user);

    Timeline getTimeline(String name, int userID);

    boolean updateTimeline(String name, int userID, int startTime, int endTime, int brightness);

    boolean addTimeline(Timeline timeline);
}
