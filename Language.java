package utilities;

import java.util.Locale;
import java.util.ResourceBundle;

/** Language Class handles language translation needs. */
public class Language {
    private static Locale locale;
    private static String localeString;
    private static String stageTitle;
    private static String paneTitle;
    private static String userNameField;
    private static String passwordField;
    private static String loginButton;
    private static String errorMessage1;
    private static String errorMessage2;
    
    /** Method to get user local. 
     * @return Locale user locale  */
    public static Locale getUserLocale() {
        locale = Locale.getDefault();
        return locale;
    }
    
    /** Method to get user location. 
     * @return String User locale */
    public static String getUserLocaleString() {
        locale = Locale.getDefault();
        localeString = locale.toString();
        return localeString;
    }
    
    /** Method to translate login screen to French. */
    public static void translateFrench() {
        ResourceBundle rb = ResourceBundle.getBundle("utilities/Language", locale);
        
        stageTitle = rb.getString("stageTitle");
        paneTitle = rb.getString("paneTitle");
        userNameField = rb.getString("userNameField");
        passwordField = rb.getString("passwordField");
        loginButton = rb.getString("loginButton");
        errorMessage1 = rb.getString("errorMessage1");
        errorMessage2 = rb.getString("errorMessage2");
    }
    
    /** Method to get stage string. 
     * @return String Stage title */
    public static String stageFr() {
        return stageTitle;
    }
    
    /** Method to get pane string. 
     * @return String Pane title */
    public static String paneFr() {
        return paneTitle;
    }
    
    /** Method to get user name string. 
     * @return String User name */
    public static String userNameFr() {
        return userNameField;
    }
    
    /** Method to get password string. 
     * @return String Password field */
    public static String passwordFr() {
        return passwordField;
    }
    
    /** Method to get login string. 
     * @return String Login button */
    public static String loginFr() {
        return loginButton;
    }
    
    /** Method to get error string. 
     * @return String Error message */
    public static String error1Fr() {
        return errorMessage1;
    }
    
    /** Method to get error string. 
     * @return String Error message */
    public static String error2Fr() {
        return errorMessage2;
    }
}
