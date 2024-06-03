package jesh.project.jeshproject.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
