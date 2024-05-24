package jesh.project.jeshproject.model;

import java.util.List;

public interface IUserDAO {

    void addUser(User user);

    User getUser(int id);

    User getUser(String identifier, UserIdentifierType identifierType);

    List<User> getAllUsers();

    User getUserByUsernameAndPassword(String username, String password);
}
