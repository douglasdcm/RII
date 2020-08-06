package data.retreive.rii;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
     /**
     * Connect to a sample database
     */
    public Connection connect(String database) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:./src/test/resources/"+database+".db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void close(Connection conn) {
        
            try {
                if (conn != null) {
                	System.out.println("Connection to SQLite has been closed.");
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
}