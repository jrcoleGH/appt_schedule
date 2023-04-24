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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.ViewAppt;
import utilities.Conversion;
import utilities.UCertifyDBQuery;

/** ViewApptController Class controls view appointment screen functions. */
public class ViewApptController implements Initializable {

    @FXML
    private Button mainMenuButton;
    @FXML
    private Button addApptButton;
    @FXML
    private Button editApptButton;
    @FXML
    private Button deleteButton;
    @FXML
    private RadioButton allRadio;
    @FXML
    private RadioButton weekRadio;
    @FXML
    private RadioButton monthRadio;
    @FXML
    private ToggleGroup viewToggle;
    @FXML
    private TableView<Appointment> viewApptTable;
    @FXML
    private TableColumn<Appointment, Integer> apptIDColumn;
    @FXML
    private TableColumn<Appointment, String> apptTitleColumn;
    @FXML
    private TableColumn<Appointment, String> apptDescColumn;
    @FXML
    private TableColumn<Appointment, String> apptLocationColumn;
    @FXML
    private TableColumn<Appointment, String> apptContactColumn;
    @FXML
    private TableColumn<Appointment, String> apptTypeColumn;
    @FXML
    private TableColumn<Appointment, String> apptStartColumn;
    @FXML
    private TableColumn<Appointment, String> apptEndColumn;
    @FXML
    private TableColumn<Appointment, Integer> apptCustIDColumn;

    /** Method to initialize the controller class. 
     * @param url
     * @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ViewAppt.clearApptList();
            Conversion.convertToAppointment(UCertifyDBQuery.getAllAppts());
        } catch (SQLException ex) {
            Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptId"));
	apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
	apptDescColumn.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
	apptLocationColumn.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
	apptContactColumn.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
	apptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("apptType"));
	apptStartColumn.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptEndColumn.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptCustIDColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));
        
        viewApptTable.setItems(ViewAppt.getAppts());
        viewApptTable.getSelectionModel().clearSelection();
    }    
    
    /** Method to handle all view menu button. */
    @FXML
    private void allView(ActionEvent event) throws SQLException {
        ViewAppt.clearApptList();
        ViewAppt.getAllAppts();
        viewApptTable.setItems(ViewAppt.getAppts());
        viewApptTable.getSelectionModel().clearSelection();
    }

    /** Method to handle week view menu button. */
    @FXML
    private void weekView(ActionEvent event) throws SQLException {
        ViewAppt.clearApptList();
        ViewAppt.getWeekAppts();
        viewApptTable.setItems(ViewAppt.getAppts());
        viewApptTable.getSelectionModel().clearSelection();
    }

    /** Method to handle month view radio button. */
    @FXML
    private void monthView(ActionEvent event) throws SQLException {
        ViewAppt.clearApptList();
        ViewAppt.getMonthAppts();
        viewApptTable.setItems(ViewAppt.getAppts());
        viewApptTable.getSelectionModel().clearSelection();
    }

    /** Method to handle main menu button. */
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

    /** Method to handle add appt button. */
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
    
    /** Method to handle edit appt button. */
    @FXML
    private void editApptHandler(ActionEvent event) throws IOException, SQLException {
        Appointment selectedAppt = viewApptTable.getSelectionModel().getSelectedItem();
        
        if(selectedAppt != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppt.fxml"));
            Parent showEditAppt = loader.load();
            Scene showApptScene = new Scene(showEditAppt);
            AddApptController controller = loader.getController();
       
            Stage apptScene = (Stage)((Node)event.getSource()).getScene().getWindow();
            apptScene.setScene(showApptScene);
            apptScene.centerOnScreen();
            apptScene.show();
       
            controller.setFields(selectedAppt);
            controller.setScreenLabel("Edit Appointment");
        }
        else {
            Alert select = new Alert(AlertType.INFORMATION);
            select.setTitle("No Appointment Selected");
            select.setHeaderText(null);
            select.setContentText("Please select an appointment to edit.");

            select.showAndWait(); 
        }  
        
        viewApptTable.getSelectionModel().clearSelection();
    }

    /** Method to handle delete appt button. */
    @FXML
    private void deleteHandler(ActionEvent event) throws SQLException {
        Appointment deleteAppt = viewApptTable.getSelectionModel().getSelectedItem();
        
        if(deleteAppt != null) {
            int apptId = deleteAppt.getApptId();
            String apptType = deleteAppt.getApptType();
            
            UCertifyDBQuery.deleteAppointment(apptId);
            
                Alert select = new Alert(AlertType.INFORMATION);
                select.setTitle("Appointment Deleted");
                select.setHeaderText(null);
                select.setContentText("Appt ID: " + apptId + " - " + apptType + " Appointment: Deleted Succesfully.");

                select.showAndWait();

                ViewAppt.clearApptList();
                Conversion.convertToAppointment(UCertifyDBQuery.getAllAppts());
                viewApptTable.setItems(ViewAppt.getAppts());
        }
        else {
            Alert select = new Alert(AlertType.INFORMATION);
            select.setTitle("No Appointment Selected");
            select.setHeaderText(null);
            select.setContentText("Please select an appointment to delete.");

            select.showAndWait(); 
        }
        
        viewApptTable.getSelectionModel().clearSelection();
    }
}
