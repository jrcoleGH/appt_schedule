package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AddCustomer;
import model.Customer;
import utilities.UCertifyDBQuery;

/** AddCustomerController Class controls add customer screen functions. */
public class AddCustomerController implements Initializable {

    @FXML
    private Label customerLabel;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button submitCustomerButton;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<String> countryCombo;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postalField;
    @FXML
    private ComboBox<String> stateCombo;
    @FXML
    private TextField phoneField;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerIdNum;
    boolean add;
    Customer customer;

    /** Method to initialize the controller class. 
     * @param url
     * @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            countryCombo.getItems().addAll(AddCustomer.getCountryNames());
        } catch (SQLException ex) {
            Logger.getLogger(AddApptController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Method to set screen label based on add/edit customer. 
     * @param name Passes name String */
    public void setScreenLabel(String name) {
        customerLabel.setText(name);
        
        add = name.equals("Add New Customer");
    }
    
    /** Method to prefill fields with selected customer when editing a customer. 
     * @param cust Passes customer object
     * @throws java.sql.SQLException */
    public void setFields(Customer cust) throws SQLException {
        this.customer = cust;
        
        customerIdLabel.setText("Customer ID:");
        customerIdNum.setText(Integer.toString(cust.getCustId()));
        countryCombo.setValue(customer.getCustCountry());
	nameField.setText(customer.getCustName());
	addressField.setText(customer.getCustAddress());
	postalField.setText(customer.getCustPostal());
        phoneField.setText(customer.getCustPhone());
        stateCombo.setValue(customer.getCustState());
        AddCustomer.setCountryId(customer.getCustCountry());
        stateCombo.getItems().addAll(AddCustomer.getDivisionNames(AddCustomer.getCountryId())); 
    }
    
    /** Method to handle the country combo box. */
    @FXML
    private void countryComboHandler(ActionEvent event) throws SQLException {
        String selectedCountry = countryCombo.getValue();
        stateCombo.getItems().clear();
        AddCustomer.setCountryId(selectedCountry);
        stateCombo.getItems().addAll(AddCustomer.getDivisionNames(AddCustomer.getCountryId()));
    }

    /** Method to handle the main menu button. */
    @FXML
    private void mainMenuHandler(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
       Parent showMenu = loader.load();
       Scene showMenuScene = new Scene(showMenu);
              
       Stage menuScene = (Stage)((Node)event.getSource()).getScene().getWindow();
       menuScene.setScene(showMenuScene);
       menuScene.centerOnScreen();
       menuScene.show();
    }
    
    /** Method to handle the submit button. */
    @FXML
    private void submitCustomerHandler(ActionEvent event) throws SQLException, IOException {
        String country = countryCombo.getValue();
	String name = nameField.getText().trim();
	String address = addressField.getText().trim();
	String postal = postalField.getText().trim();
	String phone = phoneField.getText().trim();
        String state = stateCombo.getValue();
        AddCustomer.setDivisionId(state);
        int dId = AddCustomer.getDivisionId();
        boolean fieldsSet = AddCustomer.customerTextFields(country, name, address, state, postal, phone);
        
        if(fieldsSet) {
            String currentUser = LoginController.getCurrentUser();
            
            if(!add) {
                int cId = Integer.parseInt(customerIdNum.getText());
                UCertifyDBQuery.updateCustomer(cId, name, address, postal, currentUser,phone, dId);
            }
            else {
                int lastCustId = UCertifyDBQuery.getLastCustId();
                int newCustId = lastCustId + 1;
                UCertifyDBQuery.addNewCustomer(newCustId, name, address, postal, phone, currentUser, dId);
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomer.fxml"));
            Parent showCustomer = loader.load();
            Scene showCustomerScene = new Scene(showCustomer);

            Stage viewCustomerScene = (Stage)((Node)event.getSource()).getScene().getWindow();
            viewCustomerScene.setScene(showCustomerScene);
            viewCustomerScene.centerOnScreen();
            viewCustomerScene.show();
        }
        else {
            Alert select = new Alert(Alert.AlertType.INFORMATION);
            select.setTitle("Empty Fields");
            select.setHeaderText(null);
            select.setContentText("One or more fields left empty.");

            select.showAndWait(); 
        }
    }

    /** Method to handle the back button. */
    @FXML
    private void backButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomer.fxml"));
        Parent showCustomer = loader.load();
        Scene showCustomerScene = new Scene(showCustomer);
       
        Stage viewCustomerScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        viewCustomerScene.setScene(showCustomerScene);
        viewCustomerScene.centerOnScreen();
        viewCustomerScene.show();
    }
}
