import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {
    private final String dbPath = "jdbc:sqlite:C:/Users/Ariel/Desktop/Discord-programming-bot/src/main/resources/botDB.sqlite";
    private Connection dbConnection; 
    
    public DatabaseAccess() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException eString) {
            System.err.println("Could not init JDBC driver - driver not found");
        }

        try {
           this.dbConnection = DriverManager.getConnection(this.dbPath);
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        String sql = "CREATE TABLE IF NOT EXISTS bank (id integer PRIMARY KEY, tag text NOT NULL, balance integer);";

        try  {
            // create a new table
            java.sql.Statement stmt = this.dbConnection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            this.dbConnection.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    } 
}