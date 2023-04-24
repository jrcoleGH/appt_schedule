package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/** Customer Class creates new customer objects. */
public final class Customer {
    
    private SimpleIntegerProperty custId;
    private SimpleStringProperty custName;
    private SimpleStringProperty custAddress;
    private SimpleStringProperty custPostal;
    private SimpleStringProperty custPhone;
    private SimpleStringProperty custState;
    private SimpleStringProperty custCountry;
    
    /** Constructor class. 
     * @param id
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param state
     * @param country */
    public Customer(int id, String name, String address, String postal, String phone, String state, String country) {
        this.custId = new SimpleIntegerProperty(id);
        this.custName = new SimpleStringProperty(name);
        this.custAddress = new SimpleStringProperty(address);
        this.custPostal = new SimpleStringProperty(postal);
        //this.custPhone = new SimpleStringProperty(phone);
        setCustPhone(phone);
        this.custState = new SimpleStringProperty(state);
        this.custCountry = new SimpleStringProperty(country);
    }
    
    /** Method to set customer Id. 
     * @param id Passes in customer Id */
    public void setCustId(int id) {
        custId.set(id);
    }
    
    /** Method to set customer name. 
     * @param name Passes in name String */
    public void setCustName(String name) {
        custName.set(name);
    }
    
    /** Method to set customer address. 
     * @param address Passes in address String */
    public void setCustAddress(String address) {
        custAddress.set(address);
    }
    
    /** Method to set customer postal. 
     * @param postal Passes in postal String */
    public void setCustPostal(String postal) {
        custPostal.set(postal);
    }
    
    /** Method to set customer string. 
     * @param phone Passes in phone String */
    public void setCustPhone(String phone) {
        String formattedPhone = "";
        
        for (int i=0; i<phone.length(); i++) {
            if (phone.charAt(i)>47 && phone.charAt(i)<=58) {
                formattedPhone = formattedPhone + phone.charAt(i);  
            }
        }
        
        this.custPhone = new SimpleStringProperty(formattedPhone);
    }
    
    /** Method to set customer state. 
     * @param state Passes in state String */
    public void setCustState(String state) {
        custState.set(state);
    }
    
    /** Method to set customer country. 
     * @param country Passes in country String */
    public void setCustCountry (String country) {
        custCountry.set(country);
    }
    
    /** Method to get customer Id. 
     * @return int Customer Id */
    public int getCustId() {
        return custId.get();
    }
    
    /** Method to get customer name. 
     * @return String Customer name */
    public String getCustName() {
        return custName.get();
    }
    
    /** Method to get customer address. 
     * @return String Customer address */
    public String getCustAddress() {
        return custAddress.get();
    }
    
    /** Method to get customer postal. 
     * @return String customer postal */
    public String getCustPostal() {
        return custPostal.get();
    }
    
    /** Method to get customer phone. 
     * @return String Customer phone */
    public String getCustPhone() {
        return custPhone.get();
    }
    
    /** Method to get customer state. 
     * @return String Customer state */
    public String getCustState() {
        return custState.get();
    }
    
    /** Method to get customer country. 
     * @return String Customer country */
    public String getCustCountry() {
        return custCountry.get();
    }
}
    
    
