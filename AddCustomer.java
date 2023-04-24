package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utilities.UCertifyDBQuery;

/** AddCustomer Class class allows user to add/edit a customer. */
public class AddCustomer {
    private static ResultSet rs; // result set reference
    private static String[] countriesString;
    private static String[] divisionsString;
    private static int countryId;
    private static int divisionId;
    
    /** Method to get country names that are relevant to country combo box. 
     * Lambda expression used to add array list elements to string array.
     * @return String Array of countries
     * @throws java.sql.SQLException */
    public static String[] getCountryNames() throws SQLException {
        ArrayList<String> countriesArray = new ArrayList<>();
        countriesArray.add("Canada");
        countriesArray.add("United Kingdom");
        countriesArray.add("United States");
                
        countriesString = countriesArray.toArray(c -> new String[c]);
        return countriesString;
    }
    
    /** Method to set countryId based on country name using ResultSet value. 
     * @param country Passes in country name String
     * @throws java.sql.SQLException */
    public static void setCountryId(String country) throws SQLException {
        rs = UCertifyDBQuery.getCountryName(country);
        
        while(rs.next()) {
            countryId = rs.getInt("Country_ID");
        }
    }
    
    /** Method to get countryId. 
     * @return int Country Id
     * @throws java.sql.SQLException */
    public static int getCountryId() throws SQLException {
        return countryId;
    }
    
    /** Method to get division names based on selected country using ResultSet value. 
     * Lambda expression used to add array list elements to string array.
     * @param cId Passes in Country Id
     * @return String Array of Division names
     * @throws java.sql.SQLException */
    public static String[] getDivisionNames(int cId) throws SQLException {
        rs = UCertifyDBQuery.getDivisionByCountry(cId);
        ArrayList<String> divisionsArray = new ArrayList<>();
       
        while(rs.next()) {     
            divisionsArray.add(rs.getString("Division"));     
        }
               
        divisionsString = divisionsArray.toArray(d -> new String[d]);
        return divisionsString;
    }
    
    /** Method to set divisionId based on country name using ResultSet value. 
     * @param division Passes in Division name String
     * @throws java.sql.SQLException */
    public static void setDivisionId(String division) throws SQLException {
        rs = UCertifyDBQuery.getDivisionName(division);
        
        while(rs.next()) {
            divisionId = rs.getInt("Division_ID");
        }
    }
    
    /** Method to get divisionId. 
     * @return int Division Id
     * @throws java.sql.SQLException */
    public static int getDivisionId() throws SQLException {
        return divisionId;
    }
    
    /** Method to confirm text fields are filled in properly on the add customer screen. 
     * @param country
     * @param name
     * @param address
     * @param state
     * @param postal
     * @param phone
     * @return boolean if text fields are set
     * @throws java.sql.SQLException  */
    public static boolean customerTextFields(String country, String name, String address, String state, 
            String postal, String phone) throws SQLException {
        boolean fieldsSet;
        
        fieldsSet = !(country == null || name.equals("") || address.equals("") || state == null || postal.equals("") || phone.equals(""));
                
        return fieldsSet;
    } 
}
