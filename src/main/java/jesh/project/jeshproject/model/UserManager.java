package jesh.project.jeshproject.model;

import java.util.List;

public class UserManager {
    private IUserDAO userDAO;

    public UserManager(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> searchUsers(String query) {
        return userDAO.getAllUsers()
                .stream()
                .filter(user -> isUserMatched(user, query))
                .toList();
    }

    public boolean isUserMatched(User user, String query) {
        if(query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = user.getUsername()
                + " " + user.getPassword();
        return  searchString.toLowerCase().contains(query);
    }

    public String addUser(User user) {
        if (userDAO.getUser(user.getId()) != null) { return "exists"; }
        else {
            userDAO.addUser(user);
            if (userDAO.getUser(user.getId()) == null) { return "error"; }
            else { return "added"; }
        }


    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser((user));
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }

    public User getUser(String email, UserIdentifierType userIdentifierType) {
        return userDAO.getUser(email, userIdentifierType);
    }
}