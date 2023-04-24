package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/** UCertifyDB Class initiates database information. */
public class UCertifyDB {
    private static final String serverURL = "jdbc:mysql://wgudb.ucertify.com:3306/WJ075Lv?connectionTimeZone=SERVER"; //database address
    private static final String username = "U075Lv"; //username
    private static final String password= "53688948789"; //password
    private static Connection uCertifyDB = null; //connection
    
    /** Method to connect to uCertify DB. 
     * @return Connection */
    public static Connection connectDB () {        
        try {
            uCertifyDB = DriverManager.getConnection(serverURL, username, password);
        } 
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
       
        return uCertifyDB;
    }
    
    /** Method to get connection.  
     * @return Connection */
    public static Connection getConnection() {
        return uCertifyDB;
    }
    
    /** Method to close uCertify DB. */
    public static void closeDB() {
        try {
            uCertifyDB.close();
            System.out.println("Connection to uCertify Database is Closed"); // confirm DB is closed
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
