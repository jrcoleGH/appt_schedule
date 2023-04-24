package utilities;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** UCertifyDBDefault Class handles setting default DB data for test purposes. */
public class UCertifyDBDefault {
    private static Statement stmt; // statement reference
    private static PreparedStatement prepStmt; // prepared statement reference
    private static ResultSet rs; // result set reference
    private static Connection cn; // connection reference
    private static LocalDateTime dateTime;
    private static LocalDate date;
    
    /** Method to create Statement object. 
     * @param uCertifyDB Passes in connection
     * @throws java.sql.SQLException */
    public static void setStatement(Connection uCertifyDB) throws SQLException {
        cn = UCertifyDB.connectDB();
        stmt = uCertifyDB.createStatement();
        setDefaultUsers();
        setDefaultContacts();
        setDefaultCustomers();
        //setDefaultAppts();
    }
    
    /** Method to set default users. 
     * @throws java.sql.SQLException */
    public static void setDefaultUsers() throws SQLException {

        // checking if dummy users already exist in the db
        String checkDefaultUsers = "SELECT * from users WHERE User_Name  = ? OR User_Name = ? OR User_Name = ?";
        UCertifyDBQuery.setPrepStatement(cn, checkDefaultUsers);
        prepStmt = UCertifyDBQuery.getPrepStatement();
        prepStmt.setString(1, "test");
        prepStmt.setString(2, "jennc");
        prepStmt.setString(3, "chrisc");
        rs = prepStmt.executeQuery();
        
        // adding dummy users to the db if they don't exist
        if (!rs.next()) {
            dateTime = LocalDateTime.now();
            
            String[] userNames = new String[] { "test", "jennc", "chrisc" };
            String[] passwords = new String[] { "test", "jennc", "chrisc" };
            
            String setDefaultUsers = "INSERT INTO users(User_Name, Password, Create_Date) "
                + "VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE User_Name = VALUES(User_Name), Password = VALUES(Password)";
            UCertifyDBQuery.setPrepStatement(cn, setDefaultUsers);
            prepStmt = UCertifyDBQuery.getPrepStatement();
            
            for (int i = 0; i < userNames.length; i++) {
                // Add each parameter to the row
                prepStmt.setString(1, userNames[i]);
                prepStmt.setString(2, passwords[i]);
                prepStmt.setObject(3, dateTime);
               
                // Add row to the batch
                prepStmt.addBatch();
                prepStmt.executeBatch();
            }
        }
    }
    
    /** Method to st default contacts. 
     * @throws java.sql.SQLException */
    public static void setDefaultContacts() throws SQLException {

        // checking if dummy contacts already exist in the db
        String checkDefaultContacts = "SELECT * from contacts WHERE Email = ? OR Email = ? OR Email = ?";
        UCertifyDBQuery.setPrepStatement(cn, checkDefaultContacts);
        prepStmt = UCertifyDBQuery.getPrepStatement();
        prepStmt.setString(1, "jcol627@wgu.edu");
        prepStmt.setString(2, "jessica.huff@fakemail.com");
        prepStmt.setString(3, "chris.cole@fakemail.com");
        rs = prepStmt.executeQuery();
        
        // adding dummy contacts to the db if they don't exist
        if (!rs.next()) {
                        
            String[] contactNames = new String[] { "Contact 1", "Contact 2", "Contact 3" };
            String[] emails = new String[] { "jcol627@wgu.edu", "jessica.huff@fakemail.com", "chris.cole@fakemail.com" };
				
            String setDefaultContacts = "INSERT INTO contacts(Contact_Name, Email) "
                + "VALUES(?, ?) ON DUPLICATE KEY UPDATE Contact_Name = VALUES(Contact_Name), Email = VALUES(Email)"; 
            UCertifyDBQuery.setPrepStatement(cn, setDefaultContacts);
            prepStmt = UCertifyDBQuery.getPrepStatement();
		
            for (int i = 0; i < contactNames.length; i++) {
                // Add each parameter to the row
                prepStmt.setString(1, contactNames[i]);
                prepStmt.setString(2, emails[i]);
               
                // Add row to the batch
                prepStmt.addBatch();
                prepStmt.executeBatch();
            }
        }
    }
    
    /** Method to set default customers. 
     * @throws java.sql.SQLException */
    public static void setDefaultCustomers() throws SQLException {  
        
        // checking if dummy customers already exist in the db
        String checkDefaultCustomers = "SELECT * from customers WHERE Phone = ? OR Phone = ? OR Phone = ?";
        UCertifyDBQuery.setPrepStatement(cn, checkDefaultCustomers);
        prepStmt = UCertifyDBQuery.getPrepStatement();
        prepStmt.setString(1, "813-610-0546");
        prepStmt.setString(2, "931-237-8688");
        prepStmt.setString(3, "813-748-2552");
        rs = prepStmt.executeQuery();
        
        // adding dummy customers to the db if no customers exist
        if (!rs.next()) {
            date = LocalDate.now();

            String[] names = new String[] { "Jennifer Cole", "Jessica Huff", "Chris Cole" };
            String[] addresses = new String[] { "1807 Raven Manor Dr, Dover", "12345 Vindel Cir, Ft Myers", "222 Dakota Ct, Evans" };
            String[] postalCodes = new String[] { "33527", "33905", "30809" };
            String[] phoneNums = new String[] { "813-610-0546", "931-237-8688", "813-748-2552" };
            String[] createdBy = new String[] { "test", "test", "test" };
            int[] divisionID = new int[] {3930, 3930, 3931};		

            String setDefaultCustomers = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Division_ID) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE "
                    + "Customer_Name = VALUES(Customer_Name), Address = VALUES(Address), Postal_Code = VALUES(Postal_Code), "
                    + "Create_Date = VALUES(Create_Date), Created_By = VALUES(Created_By), Division_ID = VALUES(Division_ID)"; 
            UCertifyDBQuery.setPrepStatement(cn, setDefaultCustomers);
            prepStmt = UCertifyDBQuery.getPrepStatement();

            for (int i = 0; i < names.length; i++) {
                // Add each parameter to the row
                prepStmt.setString(1, names[i]);
                prepStmt.setString(2, addresses[i]);
                prepStmt.setString(3, postalCodes[i]);
                prepStmt.setString(4, phoneNums[i]);
                prepStmt.setObject(5, date);
                prepStmt.setString(6, createdBy[i]);
                prepStmt.setInt(7, divisionID[i]);

                // Add row to the batch
                prepStmt.addBatch();
                prepStmt.executeBatch();
            } 
        }
    } 
}