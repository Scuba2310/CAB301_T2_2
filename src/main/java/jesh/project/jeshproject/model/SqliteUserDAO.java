package jesh.project.jeshproject.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {
    private Connection connection;

    public SqliteUserDAO() {
        connection = SqliteConnection.getInstance();
        createUserTable();
        createDataTable();
        // Used for testing, to be removed later
//        insertSampleData();
    }

    public void createUserTable() {
        // Create table if not exists
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

    public void createDataTable() {
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

    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM users";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO users (firstName, lastName, birthday, email, username, password) VALUES "
                    + "('John', 'Doe', '01/01/2001', 'johndoe@example.com', 'johndoe', 'jdoe1'),"
                    + "('Jane', 'Doe', '02/02/2002', 'janedoe@example.com', 'jane', 'janedoe'),"
                    + "('Jay', 'Doe', '03/03/2003', 'jaydoe@example.com', 'jayD', 'jayD')";
            insertStatement.execute(insertQuery);
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
                user = new User(
                        resultSet.getInt("userID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("birthday"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching user: " + ex.getMessage());
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
                user = new User(
                        resultSet.getInt("userID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("birthday"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching user: " + ex.getMessage());
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
                // User exists, create User object
                user = new User(
                        resultSet.getInt("userID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("birthday"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching user: " + ex.getMessage());
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            String query = "UPDATE users "
            + "SET firstName = ?, lastName = ?, birthday = ?, email = ?, username = ?, password = ? "
            + "WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getBirthday());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());
            statement.setInt(7, user.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error updating user: " + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void deleteUser(User user) {
        try {
            String query = "DELETE FROM users "
                    + "WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error deleting user: " + ex.getMessage());
        }
        try {
            String query = "DELETE FROM timelines "
                    + "WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error deleting user timelines: " + ex.getMessage());
        }
    }

    @Override
    public void logIn(User user) {
        try {
            String query = "UPDATE users "
                    + "SET loggedIn = 1 "
                    + "WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating logged in status: " + e.getMessage());
        }
    }

    @Override
    public User getLoggedInUser() {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE loggedIn = 1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // User exists, create User object
                user = new User(
                        resultSet.getInt("userID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("birthday"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error could not find logged in user: " + e.getMessage());
        }
        return user;
    }

    @Override
    public void logOut() {
        try {
            String query = "UPDATE users "
                    + "SET loggedIn = 0";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating logged in status: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("userID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("birthday"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching users: " + ex.getMessage());
        }
        return userList;
    }

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
        } catch (SQLException e) {
            System.err.println("Error adding timeline: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Timeline getTimeline(String name, int userID) {
        Timeline timeline = null;

        try {
            String query = "SELECT * FROM timelines WHERE userID = ? AND name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                timeline = new Timeline(
                        resultSet.getString("name"),
                        resultSet.getInt("startTime"),
                        resultSet.getInt("endTime"),
                        resultSet.getInt("brightness"),
                        resultSet.getInt("userID")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching timeline: " + e.getMessage());
        }

        return timeline;
    }

    public ArrayList<String> getTimelineNames() {
        ArrayList<String> names = new ArrayList<>();

        try {
            String query = "SELECT name FROM timelines WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, getLoggedInUser().getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                names.add(name);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching timelines: " + e.getMessage());
        }

        return names;
    }

    public boolean updateTimeline(Timeline timeline) {
        try {
            String query = "UPDATE timelines "
                    + "SET startTime = ?, endTime = ?, brightness = ? "
                    + "WHERE userID = ? AND name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, timeline.getStartTime());
            statement.setInt(2, timeline.getEndTime());
            statement.setInt(3, timeline.getBrightness());
            statement.setInt(4, timeline.getUserID());
            statement.setString(5, timeline.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating timeline: " + e.getMessage());
            return false;
        }
        return true;
    }


    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
