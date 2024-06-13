package jesh.project.jeshproject.model;

import java.sql.*;

public class SqliteTimelinesDAO {
    private final Connection connection;

    public SqliteTimelinesDAO() {
        connection = SqliteConnection.getInstance();
    }

    public void createTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS Timelines ("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "Name TEXT NOT NULL,"
                    + "Start_Time INTEGER NOT NULL,"
                    + "End_Time INTEGER NOT NULL,"
                    + "Brightness INTEGER NOT NULL,"
                    + "User_ID INTEGER NOT NULL"
                    + "FOREIGN KEY (User_ID) REFERENCES Users(ID)"
                    + ")";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTimeline(Timeline timeline) {
        try {
            String query = "INSERT INTO Timelines (Timeslot, Start_Time, End_Time, Brightness, User_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, timeline.getName());
            statement.setInt(2, timeline.getStartTime());
            statement.setInt(3, timeline.getEndTime());
            statement.setInt(4, timeline.getBrightness());
            statement.setInt(5, timeline.getUserID());

            statement.execute();
        } catch (SQLException ex) {
            System.err.println("Error adding timeline: " + ex.getMessage());
        }
    }

    public Timeline getTimeline(int timeslot, int userId) {
        try {
            String query = "SELECT * FROM Timelines WHERE Timeslot = ? AND User_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, timeslot);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("ID"); // Assuming the ID column name is "ID"
                String name = resultSet.getString("Name"); // Retrieving the name column
                int startTime = resultSet.getInt("Start_Time");
                int endTime = resultSet.getInt("End_Time");
                int brightness = resultSet.getInt("Brightness");

                return new Timeline(id, name, startTime, endTime, brightness, userId);
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving timeline: " + ex.getMessage());
        }
        return null;
    }

}
