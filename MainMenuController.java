package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.stage.Stage;
import model.MainMenu;

/** MainMenuController Class controls menu screen functions. */
public class MainMenuController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button addApptButton;
    @FXML
    private Button viewApptButton;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button viewCustomerButton;
    @FXML
    private Button reportsButton;

    /** Method to initialize the controller class. 
     * @param url
     * @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    /** Method to check for upcoming appt. 
     * @throws java.sql.SQLException */
    public static void upcomingAppt() throws SQLException {
        String currentUser = LoginController.getCurrentUser();
        int userId = MainMenu.getUserId(currentUser);
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
    }
    
    /** Method to handle the logout button. */
    @FXML
    private void logoutHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent showLogin = loader.load();
        Scene showLoginScene = new Scene(showLogin);
        
        // Set user location and language on login screen
        LoginController log = loader.getController();
        log.setLocationLabel();
        log.setLoginLanguage();
       
        Stage loginScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        loginScene.setScene(showLoginScene);
        loginScene.centerOnScreen();
        loginScene.show();
    }

    /** Method to handle the add appointment button. */
    @FXML
    private void addApptHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppt.fxml"));
        Parent showAddAppt = loader.load();
        Scene showAddApptScene = new Scene(showAddAppt);
        AddApptController controller = loader.getController();
       
        Stage addApptScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        addApptScene.setScene(showAddApptScene);
        addApptScene.centerOnScreen();
        addApptScene.show();
        controller.setScreenLabel("Add New Appointment");
    }

    /** Method to handle the view/adit appointments button. */
    @FXML
    private void viewApptHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAppt.fxml"));
        Parent showAppt = loader.load();
        Scene showApptScene = new Scene(showAppt);
       
        Stage viewApptScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        viewApptScene.setScene(showApptScene);
        viewApptScene.centerOnScreen();
        viewApptScene.show();
    }

    /** Method to handle the add customer button. */
    @FXML
    private void addCustomerHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
        Parent showAddCustomer = loader.load();
        Scene showAddCustomerScene = new Scene(showAddCustomer);
        AddCustomerController controller = loader.getController();
       
        Stage addCustomerScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        addCustomerScene.setScene(showAddCustomerScene);
        addCustomerScene.centerOnScreen();
        addCustomerScene.show();
        controller.setScreenLabel("Add New Customer");
    }

    /** Method to handle the view/edit customers button. */
    @FXML
    private void viewCustomerHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomer.fxml"));
        Parent showCustomer = loader.load();
        Scene showCustomerScene = new Scene(showCustomer);
       
        Stage viewCustomerScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        viewCustomerScene.setScene(showCustomerScene);
        viewCustomerScene.centerOnScreen();
        viewCustomerScene.show();
    }

    /** Method to handle the run reports button. */
    @FXML
    private void reportsHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reports.fxml"));
        Parent showReports = loader.load();
        Scene showReportsScene = new Scene(showReports);
        ReportsController log = loader.getController();
       
        Stage reportsScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        reportsScene.setScene(showReportsScene);
        reportsScene.centerOnScreen();
        reportsScene.show();
    }
}
