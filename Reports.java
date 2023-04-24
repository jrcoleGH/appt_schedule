package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.Month.*;
import utilities.UCertifyDBQuery;

/** Reports Class holds methods to run reports. */
public class Reports {
    private static ResultSet rs; // result set reference
    
    /** Method to get appointment months. 
     * @return String Appointment months
     * @throws java.sql.SQLException */
    public static String getApptMonths() throws SQLException {
        rs = UCertifyDBQuery.getAllAppts();
        String apptMonth;
        LocalDate ld;
        int jan = 0;
        int feb = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int jun = 0;
        int july = 0;
        int aug = 0;
        int sept = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;
        
        while(rs.next()) {
            ld = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
            switch (ld.getMonth()) {
                case JANUARY:
                    jan++;
                    break;
                case FEBRUARY:
                    feb++;
                    break;
                case MARCH:
                    march++;
                    break;
                case APRIL:
                    april++;
                    break;
                case MAY:
                    may++;
                    break;
                case JUNE:
                    jun++;
                    break;
                case JULY:
                    july++;
                    break;
                case AUGUST:
                    aug++;
                    break;
                case SEPTEMBER:
                    sept++;
                    break;
                case OCTOBER:
                    oct++;
                    break;
                case NOVEMBER:
                    nov++;
                    break;
                case DECEMBER: 
                    dec++;
                    break;
                default:
                    break;
            }
        }
        
        apptMonth = jan + "\t" + "January" + "\r\n" + feb + "\t" + "February" + "\r\n" + march + "\t" + "March" + "\r\n" + 
                april + "\t" + "April" + "\r\n" + may + "\t" + "May" + "\r\n" + jun + "\t" + "June" + "\r\n" + 
                july + "\t" + "July" + "\r\n" + aug + "\t" + "August" + "\r\n" + sept + "\t" + "September" + "\r\n" + 
                oct + "\t" + "October" + "\r\n" + nov + "\t" + "November" + "\r\n" + dec + "\t" + "December" + "\r\n";
        return apptMonth;
    }
    
    /** Method to get number of remote appointments. 
     * @return int Number of remote appointments
     * @throws java.sql.SQLException */
    public static int getRemoteAppts() throws SQLException {
        rs = UCertifyDBQuery.getAllAppts();
        int remote = 0;
        
        while(rs.next()) {
            if(rs.getString("Location").contains("emote")) {
                remote++;
            } 
        }
        return remote;
    }
}
