package model;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.TimeZone;
import utilities.UCertifyDBQuery;

/** AddAppt Class creates new appointments. */
public class AddAppt {
    private static ResultSet rs;
    private static ObservableList<LocalTime> apptStartTimes = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> apptEndTimes = FXCollections.observableArrayList();
    private static String[] countriesString;
    private static String[] customersString;
    private static String[] contactsString;
    private static String[] usersString;
    private static String userName;
    private static String custName;
    private static Timestamp start;
    private static Timestamp end;
    private static LocalTime startTime;
    private static LocalTime endTime;
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static LocalDateTime apptStart;
    private static LocalDateTime apptEnd;
    
    /** Method to get all country names using ResultSet value. 
     * Lambda expression used to add array list elements to string array.
     * @return String Array of country names. 
     * @throws java.sql.SQLException */
    public static String[] getCountryNames() throws SQLException {
        rs = UCertifyDBQuery.getAllCountries();
        ArrayList<String> countriesArray = new ArrayList<>();
       
        while(rs.next()) {
            countriesArray.add(rs.getString("Country"));
        }
        
        countriesString = countriesArray.toArray(c -> new String[c]);
        return countriesString;
    }
    
    /** Method to get all user names using ResultSet value. 
     * Lambda expression used to add array list elements to string array. 
     * @return String Array of user names
     * @throws java.sql.SQLException */
    public static String[] getUserNames() throws SQLException {
        rs = UCertifyDBQuery.getAllUsers();
        ArrayList<String> usersArray = new ArrayList<>();
       
        while(rs.next()) {
            usersArray.add(rs.getString("User_Name"));
        }
        
        usersString = usersArray.toArray(u -> new String[u]);
        return usersString;
    }
    
    /** Method to get user ID. 
     * @param userName Passes in username String
     * @return int User Id
     * @throws java.sql.SQLException */
    public static int getUserId(String userName) throws SQLException {
        rs = UCertifyDBQuery.getUserId(userName);
        int uId = 0;
       
        while(rs.next()) {
            uId = (rs.getInt("User_ID"));
        }
        return uId;
    }
    
    /** Method to get all customer names using ResultSet value. 
     * Lambda expression used to add array list elements to string array. 
     * @return String Array of customer names
     * @throws java.sql.SQLException */
    public static String[] getCustomerNames() throws SQLException {
        rs = UCertifyDBQuery.getAllCustomers();
        ArrayList<String> customersArray = new ArrayList<>();
       
        while(rs.next()) {
            customersArray.add(rs.getString("Customer_Name"));
        }
        
        customersString = customersArray.toArray(c -> new String[c]);
        return customersString;
    }
    
    /** Method to get customer ID. 
     * @param custName Passes in customer name String
     * @return int Customer Id
     * @throws java.sql.SQLException */
    public static int getCustomerId(String custName) throws SQLException {
        rs = UCertifyDBQuery.getCustId(custName);
        int custId = 0;
       
        while(rs.next()) {
            custId = (rs.getInt("Customer_ID"));
        }
        return custId;
    }
    
    /** Method to get all contact names using ResultSet value. 
     * Lambda expression used to add array list elements to string array. 
     * @return String Array of contact names
     * @throws java.sql.SQLException */
    public static String[] getContactNames() throws SQLException {
        rs = UCertifyDBQuery.getAllContacts();
        ArrayList<String> contactsArray = new ArrayList<>();
       
        while(rs.next()) {
            contactsArray.add(rs.getString("Contact_Name"));
        }
        
        contactsString = contactsArray.toArray(c -> new String[c]);
        return contactsString;
    }
    
    /** Method to get contact ID. 
     * @param contactName Passes in contact name String
     * @return int Contact Id
     * @throws java.sql.SQLException */
    public static int getContactId(String contactName) throws SQLException {
        rs = UCertifyDBQuery.getContactId(contactName);
        int contactId = 0;
       
        while(rs.next()) {
            contactId = (rs.getInt("Contact_ID"));
        }
        return contactId;
    }
    
    /** Method to get specific user name based on user ID. 
     * @param Id Passes in User Id
     * @return String User name
     * @throws java.sql.SQLException */
    public static String getSpecificUserName(int Id) throws SQLException {
        rs = UCertifyDBQuery.getUserName(Id);
        
        while(rs.next()) {
        userName = rs.getString("User_Name");
        }
        
        return userName;
    }
    
    /** Method to get specific customer name based on customer ID. 
     * @param Id Passes in Customer Id
     * @return String Customer name
     * @throws java.sql.SQLException */
    public static String getSpecificCustName(int Id) throws SQLException {
        rs = UCertifyDBQuery.getCustName(Id);
        
        while(rs.next()) {
        custName = rs.getString("Customer_Name");
        }
        
        return custName;
    }
    
    /** Method to get start times for time start combo box. 
     * @return ObservableList Appt start times  */
    public static ObservableList<LocalTime> getStartTimes() {
        int hrs = 23;
        int minutes = 60;
        LocalTime time;
        for(int i = 0; i < hrs; i++) {
                for(int j = 0; j < minutes; j = j+30) {
                    time = LocalTime.of(i, j);
                    apptStartTimes.add(time);
                }
        }
        return apptStartTimes;
    } 
    
    /** Method to get end times for time end combo box. 
     * @return ObservableList Appt end times */
    public static ObservableList<LocalTime> getEndTimes() {
        int hrs = 23;
        int minutes = 61;
        LocalTime time;
        for(int i = 0; i < hrs; i++) {
                for(int j = 30; j < minutes; j = j+30) {
                    if(j == 60) {
                        int newHr = i + 1;
                        int newMin = 0;
                        time = LocalTime.of(newHr, newMin);
                    }
                    else {
                        time = LocalTime.of(i, j);
                    }
                    apptEndTimes.add(time);
                }
        }
        return apptEndTimes;
    }
    
    /** Method to set time/date info based on appointment ID. 
     * @param Id Passes in appointment Id
     * @throws java.sql.SQLException */
    public static void setApptTimes(int Id) throws SQLException {
        rs = UCertifyDBQuery.getApptInfo(Id);
        
        while(rs.next()) {
            start = rs.getTimestamp("Start");
            apptStart = start.toLocalDateTime();
            startTime = start.toLocalDateTime().toLocalTime();
            startDate = start.toLocalDateTime().toLocalDate();
            
            end = rs.getTimestamp("End");
            apptEnd = end.toLocalDateTime();
            endTime = end.toLocalDateTime().toLocalTime();
            endDate = end.toLocalDateTime().toLocalDate();
        }
    }
    
    /** Method to get start time. 
     * @return LocalTime start time  */
    public static LocalTime getStartTime () {
        return startTime;
    }
    
    /** Method to get end time. 
     * @return LocalTime end time */
    public static LocalTime getEndTime () {
        return endTime;
    }
    
    /** Method to get start date. 
     * @return LocalDate start date */
    public static LocalDate getStartDate () {
        return startDate;
    }
    
    /** Method to get end date. 
     * @return LocalDate end date */
    public static LocalDate getEndDate () {
        return endDate;
    }
    
    /** Method to confirm text fields are filled in properly on the add appointment screen. 
     * @param customer
     * @param type
     * @param title
     * @param description
     * @param location
     * @param apptStartTime
     * @param apptEndTime
     * @param apptStartDate
     * @param apptEndDate
     * @param contact
     * @param user
     * @return boolean if fields are set
     * @throws java.sql.SQLException  */
    public static boolean apptTextFields(String customer, String type, String title, String description, String location, 
            LocalTime apptStartTime, LocalTime apptEndTime, LocalDate apptStartDate, LocalDate apptEndDate, 
            String contact, String user) throws SQLException {
        boolean fieldsSet;
        
         fieldsSet = !(customer == null || type.equals("") || title.equals("") || description.equals("") || location.equals("") || 
                apptStartTime == null || apptEndTime == null || apptStartDate == null || apptEndDate == null ||
                contact == null || user == null);
                
        return fieldsSet;
    } 
    
    /** Method to see if customer already has an appointment in the time range. 
     * @param custId
     * @param aId
     * @param newApptStart
     * @param newApptEnd
     * @return boolean if there is a conflict
     * @throws java.sql.SQLException */
    public static boolean conflictingAppt(int custId, int aId, LocalDateTime newApptStart, LocalDateTime newApptEnd) throws SQLException {
        rs = UCertifyDBQuery.getCustomerAppts(custId);
        int apptId;
        boolean conflict = false;
        
        while(rs.next()) {
            apptId = rs.getInt("Appointment_ID");
            start = rs.getTimestamp("Start");
            apptStart = start.toLocalDateTime();
            
            end = rs.getTimestamp("End");
            apptEnd = end.toLocalDateTime();
            
            if(apptId == aId) {
                conflict = false;
                break;
            }
            else if((apptStart.isAfter(newApptStart) || apptStart.isEqual(newApptStart)) && 
                    (apptStart.isBefore(newApptEnd) || apptStart.isEqual(newApptEnd))) {
                conflict = true;
                break;
            }   
            else if((apptEnd.isAfter(newApptStart) || apptEnd.isEqual(newApptStart)) && 
                    (apptEnd.isBefore(newApptEnd) || apptEnd.isEqual(newApptEnd))) {
                conflict = true;
                break;
            }
            else if((apptStart.isBefore(newApptStart) || apptStart.isEqual(newApptStart)) && 
                    (apptEnd.isAfter(newApptEnd) || apptEnd.isEqual(newApptEnd))) {
                conflict = true;
                break;
            }
            else if((apptStart.isAfter(newApptStart) || apptStart.isEqual(newApptStart)) && 
                    (apptEnd.isBefore(newApptEnd) || apptEnd.isEqual(newApptEnd))) {
                conflict = true;
                break;
            }
            else {
                conflict = false;
            }
        }         
        
        return conflict;
    }
    
     /** Method to see if appointment time is within office hours. 
     * @param newApptStart
     * @param newApptEnd
     * @return boolean if within office hours */
    public static boolean inOfficeHours(LocalDateTime newApptStart, LocalDateTime newApptEnd) {
        boolean officeHours;
        LocalTime officeStart = LocalTime.parse("07:59");
        LocalTime officeEnd = LocalTime.parse("22:01");
        
        ZoneId localZoneId = TimeZone.getUserZoneId();
        ZoneId easternZoneId = ZoneId.of("America/New_York");
        
        ZonedDateTime localStart = ZonedDateTime.of(newApptStart, localZoneId);
        ZonedDateTime localEnd = ZonedDateTime.of(newApptEnd, localZoneId);
        
        
        ZonedDateTime easternStart = localStart.withZoneSameInstant(easternZoneId);
        ZonedDateTime easternEnd = localEnd.withZoneSameInstant(easternZoneId);
        
        LocalTime ldtEasternStart = easternStart.toLocalDateTime().toLocalTime();
        LocalTime ldtEasternEnd = easternEnd.toLocalDateTime().toLocalTime();
        
        officeHours = (ldtEasternStart.isAfter(officeStart) && ldtEasternStart.isBefore(officeEnd) && 
                (ldtEasternEnd.isAfter(officeStart) && ldtEasternEnd.isBefore(officeEnd)));
               
        return officeHours;
    }
}