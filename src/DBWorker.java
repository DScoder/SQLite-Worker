import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by DScoder on 04.04.2016.
 */
public class DBWorker {
    private Connection c = null;
    private Statement stmt = null;



    public void createDB(String name){
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
        } catch (Exception e){
            System.out.println("Open DB Exception or ClassFound Exception");
        } finally {
            try {
                c.close();
            } catch (SQLException e){
                System.out.println("Close DB Exception");
            }
        }
    }


}
