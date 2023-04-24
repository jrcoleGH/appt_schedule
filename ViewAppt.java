package model;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.Conversion;
import utilities.UCertifyDBQuery;

/** ViewAppt Class allows user to view and edit appointments. */
public class ViewAppt {
    private static ResultSet rs; // result set reference
    private static LocalDate today;
    private static LocalDate weekOut;
    private static LocalDate monthOut;
    private static ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        
    /** Method to add new Appointment objects to the Observable list. 
     * @param newAppt Passes in appointment object
     * @throws java.sql.SQLException */
    public static void addAppt (Appointment newAppt) throws SQLException{
        allAppts.add(newAppt);
    }
    
    /** Method to clear Observable list. 
     * @throws java.sql.SQLException */
    public static void clearApptList () throws SQLException{
        allAppts.clear();
    }
    
    /** Method to return Appointment Observable list. 
     * @return ObservableList All appointments */
    public static ObservableList<Appointment> getAppts() {
        return allAppts;
    } 
    
    /** Method to return week Appointment Observable list. 
     * @throws java.sql.SQLException */
    public static void getAllAppts() throws SQLException {
        Conversion.convertToAppointment(UCertifyDBQuery.getAllAppts());
    }
    
    /** Method to return week Appointment Observable list. 
     * @throws java.sql.SQLException */
    public static void getWeekAppts() throws SQLException {
        rs = UCertifyDBQuery.getAllAppts();
        int apptId;
        Timestamp appt;
        LocalDate date;
        today = LocalDate.now();
        weekOut = today.plusWeeks(1);
        
        while(rs.next()) {
            appt = rs.getTimestamp("Start");
            apptId = rs.getInt("Appointment_ID");
            date = appt.toLocalDateTime().toLocalDate();
            
            if(date.isBefore(weekOut) || date.isEqual(weekOut)) {
                Conversion.convertToAppointment(UCertifyDBQuery.getApptInfo(apptId));
            }
        }
    } 
    
    /** Method to return month Appointment Observable list. 
     * @throws java.sql.SQLException */
    public static void getMonthAppts() throws SQLException {
        rs = UCertifyDBQuery.getAllAppts();
        int apptId;
        Timestamp appt;
        LocalDate date;
        today = LocalDate.now();        
                
        while(rs.next()) {
            appt = rs.getTimestamp("Start");
            apptId = rs.getInt("Appointment_ID");
            date = appt.toLocalDateTime().toLocalDate();
            
            if(date.getMonth() == today.getMonth()) {
                Conversion.convertToAppointment(UCertifyDBQuery.getApptInfo(apptId));
            }
        }
    } 
}
