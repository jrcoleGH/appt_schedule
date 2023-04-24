package model;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import utilities.TimeZone;
import utilities.UCertifyDBQuery;

/** MainMenu Class allows user to choose from a menu of options. */
public class MainMenu {
    private static ResultSet rs; // result set reference
    private static String userApptInfo;
    
    /** Method to see if a user has any upcoming appts.
     * @param currentUser Passes in user name String
     * @return int User Id
     * @throws java.sql.SQLException */
    public static int getUserId(String currentUser) throws SQLException {
        rs = UCertifyDBQuery.getUserId(currentUser);
        int userId = 0;
        
        if(rs.next()) {
            userId = rs.getInt("User_ID");
        }
        return userId;
    }
    
    /** Method to see if a user has any upcoming appts. 
     * @param userId Passes in user Id
     * @return boolean if user has upcoming appt
     * @throws java.sql.SQLException */
    public static boolean userHasUpcomingAppt(int userId) throws SQLException {
        rs = UCertifyDBQuery.getUserAppts(userId);
        LocalDateTime ldtAppt;
        LocalDateTime range;
        Timestamp tsAppt;
        int apptId;
        boolean upcomingAppt = false;
        
        while(rs.next()) {
            tsAppt = rs.getTimestamp("Start");
            apptId = rs.getInt("Appointment_ID");
            ldtAppt = tsAppt.toLocalDateTime();
            
            range = LocalDateTime.now().plusMinutes(16);
            
            if(ldtAppt.isAfter(LocalDateTime.now()) && ldtAppt.isBefore(range)) {
                upcomingAppt = true; 
                userApptInfo = "Appointment " + apptId + " is set for " + TimeZone.loginDateTimeString(ldtAppt); 
            }
        }
        return upcomingAppt;
    }
    
    /** Method to get upcoming user appt info. 
     * @return String Appointment info
     * @throws java.sql.SQLException */
    public static String getUserApptInfo() throws SQLException {
        return userApptInfo;
    }
}
