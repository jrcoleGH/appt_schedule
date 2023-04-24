package utilities;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/** UCertifyDBQuery Class stores database queries. */
public class UCertifyDBQuery {
    private static Connection cn = UCertifyDB.getConnection(); // connection reference
    private static PreparedStatement prepStmt; // prepared statement reference
    private static ResultSet rs; // result set reference
    private static Timestamp now; //datetime reference
    
    /** Method to set Prepared Statement object. 
     * @param uCertifyDB Passes in connection
     * @param sqlStmt Passes in sql String
     * @throws java.sql.SQLException */
    public static void setPrepStatement(Connection uCertifyDB, String sqlStmt) throws SQLException {
        prepStmt = uCertifyDB.prepareStatement(sqlStmt);    
    } 
    
    /** Method to return Prepared Statement object. 
     * @return PreparedStatement */
    public static PreparedStatement getPrepStatement() {
        return prepStmt;
    }

    
    /** Method to select all from customer table. 
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getAllCustomers() throws SQLException{
        String getAllCustomers = "SELECT * from customers";
        setPrepStatement(cn, getAllCustomers);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select all from appointment table. 
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getAllAppts() throws SQLException{
        String getAllAppts = "SELECT * from appointments";
        setPrepStatement(cn, getAllAppts);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select all from country table. 
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getAllCountries() throws SQLException{
        String getAllCountries = "SELECT * from countries";
        setPrepStatement(cn, getAllCountries);
        rs = prepStmt.executeQuery();
        return rs;
    } 
    
    /** Method to select all from user table. 
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getAllUsers() throws SQLException{
        String getAllUsers = "SELECT * from users";
        setPrepStatement(cn, getAllUsers);
        rs = prepStmt.executeQuery();
        return rs;
    } 
    
    /** Method to select all from contact table. 
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getAllContacts() throws SQLException{
        String getAllContacts = "SELECT * from contacts";
        setPrepStatement(cn, getAllContacts);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select all from division table. 
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getAllDivisions() throws SQLException{
        String getAllDivisions = "SELECT * from first_level_divisions";
        setPrepStatement(cn, getAllDivisions);
        rs = prepStmt.executeQuery();
        return rs;
    }
        
    /** Method to select specific country using country ID from country table. 
     * @param countryId Passes in country Id
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getCountryId(int countryId) throws SQLException{
        String getCountryId = "SELECT * from countries WHERE Country_ID = ?";
        setPrepStatement(cn, getCountryId);
        prepStmt.setInt(1, countryId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select specific country using country name from country table. 
     * @param countryName Passes in country name String
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getCountryName(String countryName) throws SQLException{
        String getCountryName = "SELECT * from countries WHERE Country = ?";
        setPrepStatement(cn, getCountryName);
        prepStmt.setString(1, countryName);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select specific division using division ID from division table. 
     * @param divId Passes in division Id
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getDivisionId(int divId) throws SQLException{
        String getDivisionId = "SELECT * from first_level_divisions WHERE Division_ID = ?";
        setPrepStatement(cn, getDivisionId);
        prepStmt.setInt(1, divId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select specific division using division name from division table. 
     * @param divisionName Passes in division String
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getDivisionName(String divisionName) throws SQLException{
        String getDivisionName = "SELECT * from first_level_divisions WHERE Division = ?";
        setPrepStatement(cn, getDivisionName);
        prepStmt.setString(1, divisionName);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select all divisions using country ID from division table. 
     * @param countryId Passes in country Id
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getDivisionByCountry(int countryId) throws SQLException{
        String getDivisionNames = "SELECT * from first_level_divisions WHERE COUNTRY_ID = ?";
        setPrepStatement(cn, getDivisionNames);
        prepStmt.setInt(1, countryId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select specific user name from user table using user Id. 
     * @param userId Passes in user Id
     * @return ResultSet
     * @throws java.sql.SQLException*/
    public static ResultSet getUserName(int userId) throws SQLException{
        String getUserName = "SELECT * from users WHERE User_ID = ?";
        setPrepStatement(cn, getUserName);
        prepStmt.setInt(1, userId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select specific user Id from user table using user name. 
     * @param userName Passes in user name String
     * @return ResultSet
     * @throws java.sql.SQLException*/
    public static ResultSet getUserId(String userName) throws SQLException{
        String getUserId = "SELECT * from users WHERE User_Name = ?";
        setPrepStatement(cn, getUserId);
        prepStmt.setString(1, userName);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select specific customer name from customer table using customer Id. 
     * @param custId Passes in customer Id
     * @return ResultSet
     * @throws java.sql.SQLException*/
    public static ResultSet getCustName(int custId) throws SQLException{
        String getCustName = "SELECT * from customers WHERE Customer_ID = ?";
        setPrepStatement(cn, getCustName);
        prepStmt.setInt(1, custId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select specific customer Id from customer table using customer name. 
     * @param custName Passes in customer name String
     * @return ResultSet
     * @throws java.sql.SQLException*/
    public static ResultSet getCustId(String custName) throws SQLException{
        String getCustId = "SELECT * from customers WHERE Customer_Name = ?";
        setPrepStatement(cn, getCustId);
        prepStmt.setString(1, custName);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select specific contact name using ID from contact table. 
     * @param contactId Passes in contact Id
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getContactName(int contactId) throws SQLException{
        String getContactName = "SELECT * from contacts WHERE Contact_ID = ?";
        setPrepStatement(cn, getContactName);
        prepStmt.setInt(1, contactId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Select specific contact Id from contact table using contact name. 
     * @param contactName Passes in contact name
     * @return ResultSet
     * @throws java.sql.SQLException*/
    public static ResultSet getContactId(String contactName) throws SQLException{
        String getContactId = "SELECT * from contacts WHERE Contact_Name = ?";
        setPrepStatement(cn, getContactId);
        prepStmt.setString(1, contactName);
        rs = prepStmt.executeQuery();
        return rs;
    }
        
    /** Method to select appointments by customer from appointment table. 
     * @param custId Passes in customer Id
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getCustomerAppts(int custId) throws SQLException{
        String getCustAppts = "SELECT * from appointments WHERE Customer_ID = ?";
        setPrepStatement(cn, getCustAppts);
        prepStmt.setInt(1, custId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select appointments by user from appointment table. 
     * @param userId Passes in user Id
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getUserAppts(int userId) throws SQLException{
        String getUserAppts = "SELECT * from appointments WHERE User_ID = ?";
        setPrepStatement(cn, getUserAppts);
        prepStmt.setInt(1, userId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select appointment using appointment ID. 
     * @param aId Passes in appointment Id
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getApptInfo(int aId) throws SQLException{
        String getApptInfo = "SELECT * from appointments WHERE Appointment_ID = ?";
        setPrepStatement(cn, getApptInfo);
        prepStmt.setInt(1, aId);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to select last customer id inserted into customer table. 
     * @return int Last customer Id
     * @throws java.sql.SQLException */
    public static int getLastCustId() throws SQLException{
        String lastCustId = "SELECT MAX(Customer_ID) AS Last_Cust_ID FROM customers";
        int lastId = 0;
        setPrepStatement(cn, lastCustId);
        rs = prepStmt.executeQuery();
        while(rs.next()) {
            lastId = rs.getInt("Last_Cust_ID");
        }
        return lastId;
    }
    
    /** Method to select last appointment id inserted into customer table. 
     * @return int Last appointment Id
     * @throws java.sql.SQLException */
    public static int getLastApptId() throws SQLException{
        String lastApptId = "SELECT MAX(Appointment_ID) AS Last_Appt_ID FROM appointments";
        int lastId = 0;
        setPrepStatement(cn, lastApptId);
        rs = prepStmt.executeQuery();
        while(rs.next()) {
            lastId = rs.getInt("Last_Appt_ID");
        }
        return lastId;
    }
 
    
    /** Method to update a customer in the customer table. 
     * @param cId
     * @param name
     * @param address
     * @param postal
     * @param currentUser
     * @param dId
     * @param phone
     * @throws java.sql.SQLException */
    public static void updateCustomer(int cId, String name, String address, String postal, String currentUser, 
            String phone, int dId) throws SQLException{
        now = TimeZone.localToUTC(LocalDateTime.now());
        
        String editCustomer = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, "
                + "Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        setPrepStatement(cn, editCustomer);
        prepStmt.setString(1, name);
        prepStmt.setString(2, address);
        prepStmt.setString(3, postal);
        prepStmt.setString(4, phone);
        prepStmt.setTimestamp(5, now);
        prepStmt.setString(6, currentUser);
        prepStmt.setInt(7, dId);
        prepStmt.setInt(8, cId);
        
        /** Execute update */
        prepStmt.executeUpdate();
    }
    
    /** Method to update an appointment in the appointment table. 
     * @param aId
     * @param custId
     * @param type
     * @param title
     * @param description
     * @param location
     * @param start
     * @param end
     * @param createdBy
     * @param contactId
     * @param userId
     * @throws java.sql.SQLException */
    public static void updateAppointment(int aId, int custId, String type, String title, String description, 
            String location, Timestamp start, Timestamp end, String createdBy, int contactId, int userId) throws SQLException {
        now = TimeZone.localToUTC(LocalDateTime.now());
                
        String editAppointment = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, "
                + "Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, "
                + "Contact_ID = ? WHERE Appointment_ID = ?";
        setPrepStatement(cn, editAppointment);
        prepStmt.setString(1, title);
        prepStmt.setString(2, description);
        prepStmt.setString(3, location);
        prepStmt.setString(4, type);
        prepStmt.setTimestamp(5, start);
        prepStmt.setTimestamp(6, end);
        prepStmt.setTimestamp(7, now);
        prepStmt.setString(8, createdBy);
        prepStmt.setInt(9, custId);
        prepStmt.setInt(10, userId);
        prepStmt.setInt(11, contactId);
        prepStmt.setInt(12, aId);
        
        /** Execute update */
        prepStmt.executeUpdate();
    }
    
        
    /** Method to add a new customer to the customer table. 
     * @param cId
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param currentUser
     * @param dId
     * @throws java.sql.SQLException */
    public static void addNewCustomer(int cId, String name, String address, String postal, String phone, String currentUser,
            int dId) throws SQLException{
        now = TimeZone.localToUTC(LocalDateTime.now());
        
        String addNewCustomer = "INSERT INTO customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, "
                + "Created_By, Division_ID) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; 
        setPrepStatement(cn, addNewCustomer);
        prepStmt.setInt(1, cId);
        prepStmt.setString(2, name);
        prepStmt.setString(3, address);
        prepStmt.setString(4, postal);
        prepStmt.setString(5, phone);
        prepStmt.setTimestamp(6, now);
        prepStmt.setString(7, currentUser);
        prepStmt.setInt(8, dId);
        
        /** Execute update */
        prepStmt.executeUpdate();
    }
    
    /** Method to add a new appointment to the appointment table. 
     * @param aId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param currentUser
     * @param custId
     * @param userId
     * @param contactId
     * @throws java.sql.SQLException */
    public static void addNewAppointment(int aId, String title, String description, String location, String type, 
            Timestamp start, Timestamp end, String currentUser, int custId, int userId, int contactId) throws SQLException{
        now = TimeZone.localToUTC(LocalDateTime.now());
        
        String addNewCustomer = "INSERT INTO appointments(Appointment_ID, Title, Description, Location, "
                + "Type, Start, End, Create_Date, Created_By, Customer_ID, User_ID, Contact_ID) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        setPrepStatement(cn, addNewCustomer);
        prepStmt.setInt(1, aId);
        prepStmt.setString(2, title);
        prepStmt.setString(3, description);
        prepStmt.setString(4, location);
        prepStmt.setString(5, type);
        prepStmt.setTimestamp(6, start);
        prepStmt.setTimestamp(7, end);
        prepStmt.setTimestamp(8, now);
        prepStmt.setString(9, currentUser);
        prepStmt.setInt(10, custId);
        prepStmt.setInt(11, userId);
        prepStmt.setInt(12, contactId);
        
        /** Execute update */
        prepStmt.executeUpdate();
    }
   
    
    /** Method to delete a customer from the customer table. 
     * @param custId Passes in customer Id
     * @throws java.sql.SQLException */
    public static void deleteCustomer(int custId) throws SQLException{
        String deleteCustomer = "DELETE FROM customers WHERE Customer_ID = ?"; 
        setPrepStatement(cn, deleteCustomer);
        prepStmt.setInt(1, custId);
        
        /** Execute update */
        prepStmt.executeUpdate();
    }
    
    /** Method to delete an appointment from the appointment table. 
     * @param apptId Passes in appointment Id
     * @throws java.sql.SQLException */
    public static void deleteAppointment(int apptId) throws SQLException{
        String deleteAppointment = "DELETE FROM appointments WHERE Appointment_ID = ?"; 
        setPrepStatement(cn, deleteAppointment);
        prepStmt.setInt(1, apptId);
        
        /** Execute update */
        prepStmt.executeUpdate();
    }    
    
    /** Method to get contact report info. 
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getContactReport() throws SQLException{
        String getContactReport = "SELECT Appointment_ID, Title, Type, Start, End, Customer_ID FROM appointments";
        setPrepStatement(cn, getContactReport);
        rs = prepStmt.executeQuery();
        return rs;
    }
    
    /** Method to get customer report info. 
     * @return ResultSet
     * @throws java.sql.SQLException */
    public static ResultSet getCustomerTypeReport() throws SQLException{
        String getCustomerReport = "SELECT COUNT(Appointment_ID), Type FROM appointments GROUP BY Type";
        setPrepStatement(cn, getCustomerReport);
        rs = prepStmt.executeQuery();
        return rs;
    }
}
