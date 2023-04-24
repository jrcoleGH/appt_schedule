package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.UCertifyDBQuery;

/** ViewCustomer Class allows user to view and edit customers. */
public class ViewCustomer {
    private static ResultSet rs; // result set reference
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        
    /** Method to add new customer objects to the Observable list. 
     * @param newCustomer Passes in customer object
     * @throws java.sql.SQLException */
    public static void addCustomer (Customer newCustomer) throws SQLException{
        allCustomers.add(newCustomer);
    }
    
    /** Method to clear Observable list. 
     * @throws java.sql.SQLException */
    public static void clearCustomerList () throws SQLException{
        allCustomers.clear();
    }
    
    /** Method to return Customer Observable list. 
     * @return ObservableList All customers */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }
    
    /** Method to check if a customer has an appointment. 
     * @param custId Passes in customer Id
     * @return boolean if customer has an appointment
     * @throws java.sql.SQLException */
    public static boolean customerHasAppt(int custId) throws SQLException {
        rs = UCertifyDBQuery.getCustomerAppts(custId);
        boolean hasAppt;
        
        hasAppt = rs.next();
        return hasAppt;
    }
}
