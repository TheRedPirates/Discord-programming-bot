import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {
    private final String dbPath = "jdbc:sqlite:D:/Documents/The Red Pirates 4661/Coding Team/Projects/Discord-programming-bot/src/main/resources/botDB.sqlite";
    private Connection dbConnection; 
    
    public DatabaseAccess() {
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