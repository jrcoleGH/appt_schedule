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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.ViewCustomer;
import utilities.Conversion;
import utilities.UCertifyDBQuery;

/** ViewCustomerController Class controls view customer screen functions. */
public class ViewCustomerController implements Initializable {

    @FXML
    private Button mainMenuButton;
    @FXML
    private TableView<Customer> viewCustomersTable;
    @FXML
    private TableColumn<Customer, Integer> custIDColumn;
    @FXML
    private TableColumn<Customer, String> custNameColumn;
    @FXML
    private TableColumn<Customer, String> custAddressColumn;
    @FXML
    private TableColumn<Customer, String> custStateColumn;
    @FXML
    private TableColumn<Customer, String> custPostalColumn;
    @FXML
    private TableColumn<Customer, String> custPhoneColumn;
    @FXML
    private TableColumn<Customer, String> custCountryColumn;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button editCustomerButton;
    @FXML
    private Button deleteButton;
    private static boolean alreadySet;

    /** Method to initialize the controller class. 
     * @param url 
     * @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                Conversion.convertToCustomer(UCertifyDBQuery.getAllCustomers());
            } catch (SQLException ex) {
                Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        custIDColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));
	custNameColumn.setCellValueFactory(new PropertyValueFactory<>("custName"));
	custAddressColumn.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
	custStateColumn.setCellValueFactory(new PropertyValueFactory<>("custState"));
	custPostalColumn.setCellValueFactory(new PropertyValueFactory<>("custPostal"));
	custPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("custPhone"));
	custCountryColumn.setCellValueFactory(new PropertyValueFactory<>("custCountry"));
        
        viewCustomersTable.setItems(ViewCustomer.getAllCustomers());
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

    /** Method to handle the edit customer button. */
    @FXML
    private void editCustomerHandler(ActionEvent event) throws IOException, SQLException {
        Customer selectedCust = viewCustomersTable.getSelectionModel().getSelectedItem();
        
        if(selectedCust != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
            Parent showEditCust = loader.load();
            Scene showCustScene = new Scene(showEditCust);
            AddCustomerController controller = loader.getController();
       
            Stage custScene = (Stage)((Node)event.getSource()).getScene().getWindow();
            custScene.setScene(showCustScene);
            custScene.centerOnScreen();
            custScene.show();
       
            controller.setFields(selectedCust);
            controller.setScreenLabel("Edit Customer");
        }
        else {
            Alert select = new Alert(AlertType.INFORMATION);
            select.setTitle("No Customer Selected");
            select.setHeaderText(null);
            select.setContentText("Please select a customer to edit.");

            select.showAndWait(); 
        }  
        
        viewCustomersTable.getSelectionModel().clearSelection();
    }

    /** Method to handle the delete customer button. */
    @FXML
    private void deleteCustomerHandler(ActionEvent event) throws IOException, SQLException {
        Customer deleteCust = viewCustomersTable.getSelectionModel().getSelectedItem();
        boolean hasAppt;
        
        if(deleteCust != null) {
            int custId = deleteCust.getCustId();
            hasAppt = ViewCustomer.customerHasAppt(custId);
            
            if(hasAppt) {
                Alert select = new Alert(AlertType.INFORMATION);
                select.setTitle("Customer Has Appointment");
                select.setHeaderText(null);
                select.setContentText("Selected Customer Has Scheduled Appointments: "
                        + "please delete all appointments associated with this customer first.");

                select.showAndWait();
            }
            else {
                UCertifyDBQuery.deleteCustomer(custId);
            
                Alert select = new Alert(AlertType.INFORMATION);
                select.setTitle("Customer Deleted");
                select.setHeaderText(null);
                select.setContentText("Customer Deleted Succesfully.");

                select.showAndWait();

                viewCustomersTable.getSelectionModel().clearSelection();
                Conversion.convertToCustomer(UCertifyDBQuery.getAllCustomers());
                viewCustomersTable.setItems(ViewCustomer.getAllCustomers());
            }
        }
        else {
            Alert select = new Alert(AlertType.INFORMATION);
            select.setTitle("No Customer Selected");
            select.setHeaderText(null);
            select.setContentText("Please select a customer to delete.");

            select.showAndWait(); 
        }
        
        viewCustomersTable.getSelectionModel().clearSelection();
    }
}
