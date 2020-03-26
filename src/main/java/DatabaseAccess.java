import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {
    private final String dbPath = "jdbc:sqlite:src/main/resources/botDB.sqlite";
    private Connection dbConnection;
    
    public DatabaseAccess() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException eString) {
            System.err.println("Could not init JDBC driver - driver not found");
        }
    }

    public DatabaseAccess getInstance() {
        return this;
    }

    public void open() {
        try {
            this.dbConnection = DriverManager.getConnection(this.dbPath);
        } catch (SQLException error) {
            System.out.println(error.getMessage());
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