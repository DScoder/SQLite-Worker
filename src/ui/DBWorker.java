package ui;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by DScoder on 04.04.2016.
 */
public class DBWorker {
    private Connection c = null;
    private Statement stmt = null;
    private boolean open = false;
    String[] header = null;
    ArrayList<String[]> infoList = new ArrayList<String[]>();


    public boolean isOpen() {
        return open;
    }

    public void openDB(String name) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + name + ".db");
            stmt = c.createStatement();
            System.out.println("Open table successfully");
            open = true;
        } catch (Exception e) {
            System.out.println("Open DB Exception or ClassFound Exception");
        }
    }

    public void closeDB(){
        try {
            c.close();
            open = false;
            System.out.println("DB closed");
        } catch (SQLException e) {
            System.out.println("Close DB Exception");
        }
    }

    public void reedDB(String bdName) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + bdName + ";");
            ResultSetMetaData rsmd = rs.getMetaData();
            header = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                header[i] = rsmd.getColumnName(i + 1);
            }
            infoList.clear();
            while (rs.next()) {
                String[] str = new String[5];
                str[0] = String.valueOf(rs.getInt("ID"));
                str[1] = rs.getString("Name");
                str[2] = rs.getString("Surname");
                str[3] = rs.getString("Patronymic");
                str[4] = String.valueOf(rs.getInt("PhoneNumber"));
                infoList.add(str);
            }
            open = true;
        } catch (Exception e) {
            System.out.println("Open DB Exception or ClassFound Exception");
        }
    }


    public void createDB(String name) {
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
            open = true;
        } catch (Exception e) {
            System.out.println("Open DB Exception or ClassFound Exception");
        }
    }

}
