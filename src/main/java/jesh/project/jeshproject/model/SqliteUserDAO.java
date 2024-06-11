package jesh.project.jeshproject.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {
    private final Connection connection;

    public SqliteUserDAO() {
        connection = SqliteConnection.getInstance();
        createUserTable();
        createDataTable();
    }

    private void createUserTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "userID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "birthday VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "username VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "loggedIn BIT DEFAULT 0"
                    + ")";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDataTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS timelines ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name STRING NOT NULL,"
                    + "startTime INT NOT NULL,"
                    + "endTime INT NOT NULL,"
                    + "brightness INT NOT NULL,"
                    + "userID INTEGER REFERENCES users (userID)"
                    + ")";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            String query = "INSERT INTO users (firstName, lastName, birthday, email, username, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getBirthday());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                    System.out.println("A new user was added successfully with ID: " + user.getId());
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error adding user: " + ex.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public User getUser(int id) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String birthday = resultSet.getString("birthday");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                user = new User(firstName, lastName, birthday, email, username, password);
                user.setId(id);
            }
        } catch (SQLException ex) {
            System.err.println("Error getting user: " + ex.getMessage());
        }
        return user;
    }

    @Override
    public User getUser(String identifier, UserIdentifierType identifierType) {
        User user = null;
        try {
            String query;
            if (identifierType == UserIdentifierType.EMAIL) {
                query = "SELECT * FROM users WHERE email = ?";
            } else {
                query = "SELECT * FROM users WHERE username = ?";
            }
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, identifier);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String birthday = resultSet.getString("birthday");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                user = new User(firstName, lastName, birthday, email, username, password);
                user.setId(userID);
            }
        } catch (SQLException ex) {
            System.err.println("Error getting user: " + ex.getMessage());
        }
        return user;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String birthday = resultSet.getString("birthday");
                String email = resultSet.getString("email");
                user = new User(firstName, lastName, birthday, email, username, password);
                user.setId(userID);
            }
        } catch (SQLException ex) {
            System.err.println("Error getting user: " + ex.getMessage());
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            String query = "UPDATE users SET firstName = ?, lastName = ?, birthday = ?, email = ?, username = ?, password = ? WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getBirthday());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());
            statement.setInt(7, user.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Error updating user: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public void deleteUser(User user) {
        try {
            String query = "DELETE FROM users WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println("Error deleting user: " + ex.getMessage());
        }
    }

    @Override
    public void logIn(User user) {
        try {
            String query = "UPDATE users SET loggedIn = 1 WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error logging in user: " + ex.getMessage());
        }
    }

    @Override
    public void logOut() {
        try {
            String query = "UPDATE users SET loggedIn = 0";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error logging out users: " + ex.getMessage());
        }
    }

    @Override
    public User getLoggedInUser() {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE loggedIn = 1";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String birthday = resultSet.getString("birthday");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                user = new User(firstName, lastName, birthday, email, username, password);
                user.setId(userID);
            }
        } catch (SQLException ex) {
            System.err.println("Error getting logged in user: " + ex.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String birthday = resultSet.getString("birthday");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(firstName, lastName, birthday, email, username, password);
                user.setId(userID);
                users.add(user);
            }
        } catch (SQLException ex) {
            System.err.println("Error getting all users: " + ex.getMessage());
        }
        return users;
    }

    @Override
    public boolean addTimeline(Timeline timeline) {
        try {
            String query = "INSERT INTO timelines (name, startTime, endTime, brightness, userID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, timeline.getName());
            statement.setInt(2, timeline.getStartTime());
            statement.setInt(3, timeline.getEndTime());
            statement.setInt(4, timeline.getBrightness());
            statement.setInt(5, timeline.getUserID());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    timeline.setId(generatedKeys.getInt(1));
                    System.out.println("A new timeline was added successfully with ID: " + timeline.getId());
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error adding timeline: " + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Timeline getTimeline(String name, int userID) {
        Timeline timeline = null;
        try {
            String query = "SELECT * FROM timelines WHERE name = ? AND userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int startTime = resultSet.getInt("startTime");
                int endTime = resultSet.getInt("endTime");
                int brightness = resultSet.getInt("brightness");
                timeline = new Timeline(name, startTime, endTime, brightness, userID);
                timeline.setId(id);
            }
        } catch (SQLException ex) {
            System.err.println("Error getting timeline: " + ex.getMessage());
        }
        return timeline;
    }

    @Override
    public ArrayList<String> getTimelineNames(int userID) {
        ArrayList<String> timelineNames = new ArrayList<>();
        try {
            String query = "SELECT name FROM timelines WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                timelineNames.add(resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            System.err.println("Error getting timeline names: " + ex.getMessage());
        }
        return timelineNames;
    }

    @Override
    public boolean updateTimeline(Timeline timeline) {
        try {
            String query = "UPDATE timelines SET startTime = ?, endTime = ?, brightness = ? WHERE name = ? AND userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, timeline.getStartTime());
            statement.setInt(2, timeline.getEndTime());
            statement.setInt(3, timeline.getBrightness());
            statement.setString(4, timeline.getName());
            statement.setInt(5, timeline.getUserID());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing timeline was updated successfully!");
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Error updating timeline: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public void deleteTimeline(int timelineId) {
        try {
            String query = "DELETE FROM timelines WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, timelineId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error deleting timeline: " + ex.getMessage());
        }
    }

}

