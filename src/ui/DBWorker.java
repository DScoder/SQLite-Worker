package ui;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by DScoder on 04.04.2016.
 */
public class DBWorker {
    private Connection c = null;
    private Statement stmt = null;
    ArrayList<String> info = new ArrayList<String>();


    public boolean createDB(String name) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + name + ".db");
            System.out.println("Open database successfully");
            stmt = c.createStatement();
            stmt.execute("CREATE TABLE " + name +
                    " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name STRING, " +
                    "Surname INTEGER, " +
                    "Patronymic STRING, " +
                    "PhoneNumber INT UNIQUE);");
            System.out.println("Create or open table successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Open DB Exception or ClassFound Exception");
            return false;
        } finally {
            try {
                c.close();
                return true;
            } catch (SQLException e) {
                System.out.println("Close DB Exception");
                return false;
            }
        }
    }

    public void reedDB(String bdName) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + bdName + ".db");
            System.out.println("Open database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + bdName + ";");
            while (rs.next()) {
                String string = rs.getInt("ID") + " "
                        + rs.getString("Name") + " "
                        + rs.getString("Surname") + " "
                        + rs.getString("Patronymic") + " "
                        + rs.getInt("PhoneNumber");
                info.add(string);
            }
        } catch (Exception e) {
            System.out.println("Open DB Exception or ClassFound Exception");
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                System.out.println("Close DB Exception");
            }
        }
    }

}
