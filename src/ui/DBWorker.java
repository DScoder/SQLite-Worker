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
    private int id = 0;
    String[] header = null;
    ArrayList<String[]> infoList = new ArrayList<String[]>();
    ResultSet rs;


    public boolean isOpen() {
        return open;
    }

    public void openDB(String name) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + name + ".db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            createTable(name);
            System.out.println("Open table successfully");
            open = true;
        } catch (Exception e) {
            System.out.println("Open DB Exception or ClassFound Exception");
        }
    }

    public void closeDB(){
        try {
            c.commit();
            c.close();
            open = false;
            System.out.println("DB closed");
        } catch (SQLException e) {
            System.out.println("Close DB Exception");
        }
    }

    public void reedDB(String name) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + name + ";");
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
                if(rs.getInt("ID") > id) id = rs.getInt("ID");
            }
            open = true;
        } catch (Exception e) {
            System.out.println("Open DB Exception or ClassFound Exception");
        }
    }


    public void createTable(String name) {
        try {
            stmt.execute("CREATE TABLE " + name +
                    " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name STRING, " +
                    "Surname INTEGER, " +
                    "Patronymic STRING, " +
                    "PhoneNumber INT UNIQUE);");
            System.out.println("Create or open table successfully");
        } catch (SQLException e) {
            System.out.println("Table already created.");
        }
    }

    public void addData(String name){
        try {
        stmt.executeUpdate("INSERT INTO " + name +
                " (Name, Surname, Patronymic, PhoneNumber) " +
                "VALUES ( 'Allen" + id + "' , 'Predator" + id + "', 'Texas" + id + "', 15434" + id + " );");
            System.out.println("Data was added");
            id++;
        } catch (SQLException e) {
            System.out.println("Add data exception.");
        }
    }

}
