package jesh.project.jeshproject.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {
    private Connection connection;

    public SqliteUserDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    public void createTable() {
        // Create table if not exists
        try {
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "birthday VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "username VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL"
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
    public void addUser(User user) {
        try {
            String query = "INSERT INTO users (firstName, lastName, birthday, email, username, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getBirthday());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());

            statement.execute();
        } catch (SQLException ex) {
            System.err.println("Error adding user: " + ex.getMessage());
        }
    }


    @Override
    public User getUser(int id) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
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
                        resultSet.getInt("id"),
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
                        resultSet.getInt("id"),
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
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
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

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
