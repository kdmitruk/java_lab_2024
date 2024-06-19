package pl.umcs.oop.databasecreator;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Creator {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:/tmp/usereeg.db";
        Creator creator = new Creator();
        creator.create(url);

    }
    public void create(String url){
        String createTableSQL = "CREATE TABLE IF NOT EXISTS user_eeg ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL,"
                + "electrode_number INTEGER NOT NULL,"
                + "image TEXT NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Ok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String url) {
        String filepath = url.substring(url.indexOf("\\"));
        File dbFile = new File(filepath);
        if (dbFile.exists()) {
            if (!dbFile.delete()){
                System.out.println("Error during delete database");
            }
        }else{
            System.out.println("Error database dosent exist");
        }
    }
}