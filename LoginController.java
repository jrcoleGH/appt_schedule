package view_controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import model.MainMenu;
import utilities.Language;
import utilities.TimeZone;
import utilities.UCertifyDBQuery;

/** LoginController Class controls login screen functions. */
public class LoginController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label locationLabel;
    @FXML
    private TitledPane paneTitle;
    private static String currentUser;
    boolean french;

    /** Method to initialize the controller class. 
     * @param url
     * @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    /** Method to set location label. */
    public void setLocationLabel() {
        locationLabel.setText(TimeZone.getUserZoneIdString() + " - " + Language.getUserLocaleString());
    }
    
    /** Method to set login language. */
    public void setLoginLanguage() {
        if(Language.getUserLocale().getLanguage().equals("fr")) {
            Language.translateFrench();
            paneTitle.setText(Language.paneFr());
            userNameField.setPromptText(Language.userNameFr());
            passwordField.setPromptText(Language.passwordFr());
            loginButton.setText(Language.loginFr());
            french = true;
        } 
    }

    /** Method to handle login button. */
    @FXML
    private void loginHandler(ActionEvent event) throws IOException, SQLException {        
        String userName = userNameField.getText().trim();
        String password = passwordField.getText().trim();
        String loginAttempt;
       
        if(userName.equals("") || password.equals("")) {
            loginAttempt = "Login attempt was unsuccesful";
            trackLogin(userName, password, loginAttempt);
            
            if(french) {
                Alert select = new Alert(Alert.AlertType.INFORMATION);
                select.setTitle("Empty Fields");
                select.setHeaderText(null);
                select.setContentText(Language.error2Fr());

                select.showAndWait();            
            }
            else{
                Alert select = new Alert(Alert.AlertType.INFORMATION);
                select.setTitle("Empty Fields");
                select.setHeaderText(null);
                select.setContentText("User Name or Password was not entered.");

                select.showAndWait(); 
            }
        }
        else if(isUserValid(userName, password)) {
            setCurrentUser(userName);
            loginAttempt = "Login attempt was succesful";
            trackLogin(userName, password, loginAttempt);
            
            int userId = MainMenu.getUserId(userName);
            boolean hasAppt = MainMenu.userHasUpcomingAppt(userId);
            
            if(hasAppt) {
                String apptInfo = MainMenu.getUserApptInfo();

                Alert select = new Alert(Alert.AlertType.INFORMATION);
                select.setTitle("Upcoming Appointment");
                select.setHeaderText(null);
                select.setContentText(apptInfo);

                select.showAndWait();
            }
            else {
                Alert select = new Alert(Alert.AlertType.INFORMATION);
                select.setTitle("No Upcoming Appointments");
                select.setHeaderText(null);
                select.setContentText("No Upcoming Appointments.");

                select.showAndWait(); 
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent showMenu = loader.load();
            Scene showMenuScene = new Scene(showMenu);

            Stage menuScene = (Stage)((Node)event.getSource()).getScene().getWindow();
            menuScene.setScene(showMenuScene);
            menuScene.centerOnScreen();
            menuScene.show();
        }
        else {    
            loginAttempt = "Login attempt was unsuccesful";
            trackLogin(userName, password, loginAttempt);
            
            if(french) {
                Alert select = new Alert(Alert.AlertType.INFORMATION);
                select.setTitle("Incorrect Entry");
                select.setHeaderText(null);
                select.setContentText(Language.error1Fr());

                select.showAndWait();
            }
            else {
                Alert select = new Alert(Alert.AlertType.INFORMATION);
                select.setTitle("Incorrect Entry");
                select.setHeaderText(null);
                select.setContentText("Incorrect User Name or Password was entered.");

                select.showAndWait();
            }
        }
    }
    
    /** Method to set current user info. 
     * @param user String */
    private void setCurrentUser(String user) {
        currentUser = user;
    }
    
    /** Method to get current user info. 
     * @return String Current user */
    public static String getCurrentUser() {
        return currentUser;
    }
    
    /** Method to check if a user is valid. 
     * @param user
     * @param password
     * @return boolean is user is valid
     * @throws java.sql.SQLException  */
    public static boolean isUserValid(String user, String password) throws SQLException {
        ResultSet rs = UCertifyDBQuery.getAllUsers();
        String[] userNamesList;
        String[] passwordsList;
        ArrayList<String> userNamesArray = new ArrayList<>();
        ArrayList<String> passwordsArray = new ArrayList<>();
        boolean valid;
        
        while(rs.next()) {     
            userNamesArray.add(rs.getString("User_Name")); 
            passwordsArray.add(rs.getString("Password"));
        }
        
        userNamesList = userNamesArray.toArray(new String[0]);
        passwordsList = passwordsArray.toArray(new String[0]);
        
        // Checking if usersList array contains user name used to log in
        if(Arrays.asList(userNamesList).contains(user)) {
            int pwIndex = Arrays.asList(userNamesList).indexOf(user);
            
            valid = passwordsList[pwIndex].equals(password);
        }
        else {
            valid = false;
        }
               
        return valid;
    }
    
    /** Method to track login attempts. 
     * @param name
     * @param pw
     * @param attempt
     * @throws java.io.IOException */
    private void trackLogin(String name, String pw, String attempt) throws IOException {
        String loginTrack = attempt + " by User '" + name + "' with Password '" + pw + "' - Date and Time of attempt: " 
                + TimeZone.loginDateTimeString(LocalDateTime.now(ZoneOffset.UTC)) + " UTC";
        
        FileWriter loginFile = new FileWriter("login_activity.txt", true);
        try (PrintWriter loginPrint = new PrintWriter(loginFile)) {
            loginPrint.println(loginTrack);
        }
    }
}
