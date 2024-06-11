package jesh.project.jeshproject.model;

import java.sql.*;

public class SqliteTimelinesDAO {
    private Connection connection;


    public SqliteTimelinesDAO() {
        connection = SqliteConnection.getInstance();

    }

    public void createTable() {
        // Create table if not exists
        try {
            String query = "CREATE TABLE IF NOT EXISTS Timelines ("
                    + "Timeline_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "Timeslot INTEGER NOT NULL,"
                    + "Start_Time VARCHAR NOT NULL,"
                    + "End_Time VARCHAR NOT NULL,"
                    + "User_ID VARCHAR NOT NULL"
                    + ")";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTimeline(Timeline timeline) {
        try {
            String query = "INSERT INTO Timelines (Timeslot, Start_Time, End_Time, User_ID) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, timeline.getTimeslot());
            statement.setString(2, timeline.getStart_Time());
            statement.setString(3, timeline.getEnd_Time());
            statement.setInt(4, timeline.getUser_ID());

            statement.execute();
        } catch (SQLException ex) {
            System.err.println("Error adding user: " + ex.getMessage());
        }
    }
}
