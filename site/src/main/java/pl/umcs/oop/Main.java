package pl.umcs.oop;

import pl.umcs.oop.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.connect("my.db");

        try {
            String createSQLTable = "CREATE TABLE users( "+
                                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                                    "username TEXT NOT NULL,"+
                                    "password TEXT NOT NULL)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(createSQLTable);
            statement.executeUpdate();

            String insertSQL = "INSERT INTO users(username, password)"+
                                "VALUES (?, ?)";
            statement = dbConnection.getConnection().prepareStatement(insertSQL);

            statement.setString(1,"jan_kow");
            statement.setString(2,"124hcsk");
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbConnection.disconnect();
    }
}