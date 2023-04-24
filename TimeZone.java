package utilities;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** TimeZone Class handles timezone needs. */
public class TimeZone {
    private static ZoneId zoneId;
    private static ZonedDateTime localZDT;
    private static Instant localToUTC;
    private static Timestamp tsUTC;
    private static String zoneString;
    private static String dtString;
    private static DateTimeFormatter loginDateTime;
    private static DateTimeFormatter calendarDateTime;
    
    /** Method to get user zone Id. 
     * @return ZoneId */
    public static ZoneId getUserZoneId() {
        zoneId = ZoneId.systemDefault();
        return zoneId;   
    }
    
    /** Method to get user zone Id string. 
     * @return String zoneId */
    public static String getUserZoneIdString() {
        zoneId = ZoneId.systemDefault();
        zoneString = zoneId.toString();
        return zoneString;   
    }
    
    /** Method to convert local time to UTC time. 
     * @param dt Passes in localdatetime
     * @return Timestamp UTC time */
    public static Timestamp localToUTC(LocalDateTime dt) {
        localZDT = ZonedDateTime.of(dt, zoneId);
        localToUTC = localZDT.toInstant();
        tsUTC = Timestamp.from(localToUTC);
        
        return tsUTC;
    }
    
    /** Method to convert LocalDateTime to a string - this method used to record user login attempts. 
     * @param ldt Passes in localdatetime
     * @return String localdatetime */
    public static String loginDateTimeString(LocalDateTime ldt) {
        loginDateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        dtString = ldt.format(loginDateTime);
        return dtString;
    }
    
    /** Method to convert LocalDateTime to a string - this method used for calendar view. 
     * @param apptDateTime Passes in localdatetime
     * @return String localdatetime */
    public static String calendarDateTimeString(LocalDateTime apptDateTime) {
        calendarDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dtString = apptDateTime.format(calendarDateTime);
        return dtString;
    }
}
