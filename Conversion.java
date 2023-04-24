package utilities;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Appointment;
import model.ViewAppt;
import model.Customer;
import model.ViewCustomer;

/** Conversion Class converts result sets to customer and appointment objects. */
public class Conversion {
    private static ResultSet rs; // result set reference
    private static int custId;
    private static String custName;
    private static String custAddress;
    private static String custPostal;
    private static String custPhone;
    private static int divId;
    private static int countryId;
    private static String custState;
    private static String custCountry;
    private static int apptId;
    private static String apptTitle;
    private static String apptDescription;
    private static String apptLocation;
    private static int contactId;
    private static String apptContact;
    private static String apptType;
    private static Timestamp apptStart;
    private static Timestamp apptEnd;
    private static String dtApptStart;
    private static String dtApptEnd;
    private static int userId;
    
    /** Method to create customer object. 
     * @param resultset Passes in result set
     * @throws java.sql.SQLException */
    public static void convertToCustomer(ResultSet resultset) throws SQLException {
        ViewCustomer.clearCustomerList();
        
        while(resultset.next()) {
            custId = resultset.getInt("Customer_ID");
            custName = resultset.getString("Customer_Name");
            custAddress = resultset.getString("Address");
            custPostal = resultset.getString("Postal_Code");
            custPhone = resultset.getString("Phone"); 
            divId = resultset.getInt("Division_ID");
            convertToState();
            convertToCountry();
            
            Customer customer = new Customer(custId, custName, custAddress, custPostal, custPhone, custState, custCountry);
            ViewCustomer.addCustomer(customer);
        }
    }
    
    /** Method to create appointment object. 
     * @param resultset Passes in result set
     * @throws java.sql.SQLException */
    public static void convertToAppointment(ResultSet resultset) throws SQLException {
        
        while(resultset.next()) {
            custId = resultset.getInt("Customer_ID");
            apptId = resultset.getInt("Appointment_ID");
            userId = resultset.getInt("User_ID");
            contactId = resultset.getInt("Contact_ID");
            apptTitle = resultset.getString("Title");
            apptDescription = resultset.getString("Description");
            apptLocation = resultset.getString("Location");
            apptType = resultset.getString("Type");
            apptStart = resultset.getTimestamp("Start");
            apptEnd = resultset.getTimestamp("End");
            
            dtApptStart = TimeZone.calendarDateTimeString(apptStart.toLocalDateTime());
            dtApptEnd = TimeZone.calendarDateTimeString(apptEnd.toLocalDateTime());
                    
            convertToContactName();
           
            Appointment appointment = new Appointment(apptId, apptTitle, apptDescription, apptLocation, 
                    apptContact, apptType, custId, dtApptStart, dtApptEnd);
            appointment.setUserId(userId);
            ViewAppt.addAppt(appointment);
        }
    }
    
    /** Method to convert divID to state/province using ResultSet value. 
     * @throws java.sql.SQLException */
    public static void convertToState() throws SQLException {
        rs = UCertifyDBQuery.getDivisionId(divId);
        
        while(rs.next()) {
        countryId = rs.getInt("Country_ID");
        custState = rs.getString("Division");
        }
    }
    
    /** Method to convert countryID to country using ResultSet value. 
     * @throws java.sql.SQLException */
    public static void convertToCountry() throws SQLException {
        rs = UCertifyDBQuery.getCountryId(countryId);
        
        while(rs.next()) {
        custCountry = rs.getString("Country");
        }
    }
    
    /** Method to convert contacID to contact name using ResultSet value. 
     * @throws java.sql.SQLException */
    public static void convertToContactName() throws SQLException {
        rs = UCertifyDBQuery.getContactName(contactId);
        
        while(rs.next()) {
        apptContact = rs.getString("Contact_Name");
        }
    }
}
