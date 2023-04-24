package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/** AddAppt Class creates new appointment objects. */
public class Appointment {
    private SimpleIntegerProperty apptId;
    private SimpleStringProperty apptTitle;
    private SimpleStringProperty apptDescription;
    private SimpleStringProperty apptLocation;
    private SimpleStringProperty apptContact;
    private SimpleStringProperty apptType;
    private SimpleIntegerProperty custId;
    private SimpleStringProperty apptStart;
    private SimpleStringProperty apptEnd;
    private int userId;
    
    /** Constructor class. 
     * @param aId
     * @param title
     * @param description
     * @param location
     * @param contactId
     * @param type
     * @param customerId
     * @param start
     * @param end */
    public Appointment(int aId, String title, String description, String location, 
            String contactId, String type, int customerId, String start, String end) {
        this.apptId = new SimpleIntegerProperty(aId);
        this.apptTitle = new SimpleStringProperty(title);
        this.apptDescription = new SimpleStringProperty(description);
        this.apptLocation = new SimpleStringProperty(location);
        this.apptContact = new SimpleStringProperty(contactId);
        this.apptType = new SimpleStringProperty(type);
        this.custId = new SimpleIntegerProperty(customerId);
        this.apptStart = new SimpleStringProperty(start);
        this.apptEnd = new SimpleStringProperty(end);
    }
    
    /** Method to set appointment ID. 
     * @param aId Passes in appointment Id */
    public void setApptId(int aId) {
        apptId.set(aId);
    }
    
    /** Method to set appointment title. 
     * @param title Passes in title String */
    public void setApptTitle(String title) {
        apptTitle.set(title);
    }
    
    /** Method to set appointment description. 
     * @param description Passes in description String */
    public void setApptDescription(String description) {
        apptDescription.set(description);
    }
    
    /** Method to set appointment location. 
     * @param location Passes in location String */
    public void setApptLocation(String location) {
        apptLocation.set(location);
    }
    
    /** Method to set appointment contact. 
     * @param contact Passes in contact String */
    public void setApptContact(String contact) {
        apptContact.set(contact);
    }
    
    /** Method to set appointment type. 
     * @param type Passes in type String */
    public void setApptType(String type) {
        apptType.set(type);
    }
    
    /** Method to set appointment customer ID. 
     * @param cId Passes in customer Id */
    public void setCustId(int cId) {
        custId.set(cId);
    }
    
    /** Method to set appointment start. 
     * @param start Passes in  appointment start String*/
    public void setApptStart(String start) {
        apptStart.set(start);
    }
    
    /** Method to set appointment end. 
     * @param end Passes in appointment end String */
    public void setApptEnd(String end) {
        apptEnd.set(end);
    }
    
    /** Method to set appointment user ID. 
     * @param uId Passes in user Id */
    public void setUserId(int uId) {
        this.userId = uId;
    }
    
    /** Method to get appointment ID. 
     * @return int Appointment Id */
    public int getApptId() {
        return apptId.get();
    }
    
    /** Method to get appointment title. 
     * @return String Appointment title  */
    public String getApptTitle() {
        return apptTitle.get();
    }
    
    /** Method to get appointment description. 
     * @return String Appointment description */
    public String getApptDescription() {
        return apptDescription.get();
    }
    
    /** Method to get appointment location. 
     * @return String Appointment location */
    public String getApptLocation() {
        return apptLocation.get();
    }
    
    /** Method to get appointment contact. 
     * @return String Appointment contact */
    public String getApptContact() {
        return apptContact.get();
    }
    
    /** Method to get appointment type. 
     * @return String Appointment type */
    public String getApptType() {
        return apptType.get();
    }
    
    /** Method to get appointment customer ID. 
     * @return int Customer Id */
    public int getCustId() {
        return custId.get();
    }
    
    /** Method to get appointment start. 
     * @return String Appointment start */
    public String getApptStart() {
        return apptStart.get();
    }
    
    /** Method to get appointment end. 
     * @return String Appointment end */
    public String getApptEnd() {
        return apptEnd.get();
    }
    
    /** Method to get appointment user ID. 
     * @return int User Id */
    public int getUserId() {
        return userId;
    }
}
