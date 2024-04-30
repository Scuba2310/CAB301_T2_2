package jesh.project.jeshproject.model;

import java.util.List;


public interface IUserDAO {

    void addUser(User user);

    User getUser(int id);

    List<User> getAllUsers();
}