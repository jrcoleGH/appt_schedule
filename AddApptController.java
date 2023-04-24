package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.AddAppt;
import model.Appointment;
import model.ViewCustomer;
import utilities.TimeZone;
import utilities.UCertifyDBQuery;

/** AddApptController Class controls add appointment screen functions. */
public class AddApptController implements Initializable {

    @FXML
    private Label apptLabel;
    @FXML
    private Label apptIdLabel;
    @FXML
    private Label apptIdNum;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button submitApptButton;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<String> chooseCustCombo;
    @FXML
    private TextField apptTypeField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descField;
    @FXML
    private TextField locationField;
    @FXML
    private ComboBox<String> contactCombo;
    @FXML
    private ComboBox<String> userCombo;
    @FXML
    private DatePicker startDate;
    @FXML
    private ComboBox<LocalTime> startTime;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox<LocalTime> endTime;
    boolean add;
    Appointment appointment;
    LocalDate minDate = LocalDate.now();
    LocalDate maxDate = minDate.plusYears(2);

    /** Method to initialize the controller class. 
     * @param url
     * @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chooseCustCombo.getItems().addAll(AddAppt.getCustomerNames());
            contactCombo.getItems().addAll(AddAppt.getContactNames());
            userCombo.getItems().addAll(AddAppt.getUserNames());
            startTime.getItems().addAll(AddAppt.getStartTimes());
            endTime.getItems().addAll(AddAppt.getEndTimes());
            setAvailableDates();
        } catch (SQLException ex) {
            Logger.getLogger(AddApptController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    /** Method to set screen label based on add/edit appointment. 
     * @param name Passes in name String */
    public void setScreenLabel(String name) {
        apptLabel.setText(name);
        
        add = name.equals("Add New Appointment");
    }
    
    /** Method to prefill fields with selected appointment info when editing an appt. 
     * @param appt Passes in appointment object
     * @throws java.sql.SQLException */
    public void setFields(Appointment appt) throws SQLException {
        this.appointment = appt;
        
        apptIdLabel.setText("Appointment ID:");
        apptIdNum.setText(Integer.toString(appointment.getApptId()));
        chooseCustCombo.setValue(AddAppt.getSpecificCustName(appointment.getCustId()));
	apptTypeField.setText(appointment.getApptType());
	titleField.setText(appointment.getApptTitle());
	descField.setText(appointment.getApptDescription());
        locationField.setText(appointment.getApptLocation());
        contactCombo.setValue(appointment.getApptContact());
        userCombo.setValue(AddAppt.getSpecificUserName(appointment.getUserId()));
        AddAppt.setApptTimes(appointment.getApptId());
        startDate.setValue(AddAppt.getStartDate());
        endDate.setValue(AddAppt.getEndDate());
        startTime.setValue(AddAppt.getStartTime());
        endTime.setValue(AddAppt.getEndTime());
    }
    
    /** Method to restrict dates available in date picker fields - example found via Oracle javadocs.  
     * Lambda expression used via oracle javadocs recommendation. */
    public void setAvailableDates() {
        final Callback<DatePicker, DateCell> dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(minDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #c4c3d0;");
                }else if (item.isAfter(maxDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #c4c3d0;");
                }
            }
        };
        startDate.setDayCellFactory(dayCellFactory);
        endDate.setDayCellFactory(dayCellFactory); 
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
    private void submitApptHandler(ActionEvent event) throws SQLException, IOException {
        String customer = chooseCustCombo.getValue();
        int custId = AddAppt.getCustomerId(customer);
        String type = apptTypeField.getText().trim();
        String title = titleField.getText().trim();
        String description = descField.getText().trim();
        String location = locationField.getText().trim();
        LocalTime apptStartTime = startTime.getValue();
        LocalTime apptEndTime = endTime.getValue();
        LocalDate apptStartDate = startDate.getValue();
        LocalDate apptEndDate = endDate.getValue();
        String contact = contactCombo.getValue();
        int contactId = AddAppt.getContactId(contact);
        String user = userCombo.getValue();
        int userId = AddAppt.getUserId(user);
        boolean fieldsSet = AddAppt.apptTextFields(customer, type, title, description, location, apptStartTime, apptEndTime,
                apptStartDate, apptEndDate, contact, user);
        
        if(fieldsSet) {
            LocalDateTime start = LocalDateTime.of(apptStartDate, apptStartTime);
            LocalDateTime end = LocalDateTime.of(apptEndDate, apptEndTime);
            Timestamp tsStart = TimeZone.localToUTC(start);
            Timestamp tsEnd = TimeZone.localToUTC(end);
            String currentUser = LoginController.getCurrentUser();
            int aId;
            
            if(!add) {
            aId = Integer.parseInt(apptIdNum.getText());
            }
            else {
                int lastApptId = UCertifyDBQuery.getLastApptId();
                aId = lastApptId + 1;
            }
            
            boolean hasAppt = ViewCustomer.customerHasAppt(custId);
            boolean conflict = AddAppt.conflictingAppt(custId, aId, start, end);
            boolean officeHours = AddAppt.inOfficeHours(start, end);
            
            if(hasAppt) {
                if(conflict) {
                    Alert select = new Alert(Alert.AlertType.INFORMATION);
                    select.setTitle("Appointment Conflict");
                    select.setHeaderText(null);
                    select.setContentText("Chosen customer already has an appointment within the selected time frame.");

                    select.showAndWait();
                }
                else if(!officeHours) {
                    Alert select = new Alert(Alert.AlertType.INFORMATION);
                    select.setTitle("Office Hours Conflict");
                    select.setHeaderText(null);
                    select.setContentText("Appointment time must be between 8am - 10pm EST.");

                    select.showAndWait();
                }
                else {
                    if(!add) {
                    UCertifyDBQuery.updateAppointment(aId, custId, type, title, description, location, 
                        tsStart, tsEnd, currentUser, contactId, userId);
                }
                    else {
                        UCertifyDBQuery.addNewAppointment(aId, title, description, location, type, 
                            tsStart, tsEnd, currentUser, custId, userId, contactId);
                    }

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAppt.fxml"));
                    Parent showAppt = loader.load();
                    Scene showApptScene = new Scene(showAppt);

                    Stage viewApptScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                    viewApptScene.setScene(showApptScene);
                    viewApptScene.centerOnScreen();
                    viewApptScene.show();
                }
            }
            else{
                if(officeHours) { 
                    if(!add) {
                        UCertifyDBQuery.updateAppointment(aId, custId, type, title, description, location, 
                            tsStart, tsEnd, currentUser, contactId, userId);
                    }
                    else {
                        UCertifyDBQuery.addNewAppointment(aId, title, description, location, type, 
                            tsStart, tsEnd, currentUser, custId, userId, contactId);
                    }
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAppt.fxml"));
                    Parent showAppt = loader.load();
                    Scene showApptScene = new Scene(showAppt);

                    Stage viewApptScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                    viewApptScene.setScene(showApptScene);
                    viewApptScene.centerOnScreen();
                    viewApptScene.show();
                }
                else {
                    Alert select = new Alert(Alert.AlertType.INFORMATION);
                    select.setTitle("Office Hours Conflict");
                    select.setHeaderText(null);
                    select.setContentText("Appointment time must be between 8am - 10pm EST.");

                    select.showAndWait();
                }
            }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAppt.fxml"));
        Parent showAppt = loader.load();
        Scene showApptScene = new Scene(showAppt);
       
        Stage viewApptScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        viewApptScene.setScene(showApptScene);
        viewApptScene.centerOnScreen();
        viewApptScene.show();
    }

    /** Method to handle startDate date picker, if adjusted - example found via Oracle javadocs.  
     * Lambda expression used oracle javadocs recommendation. */
    @FXML
    private void startDateHandler(ActionEvent event) {
        LocalDate newStart = startDate.getValue();
        endDate.setValue(null);
        
        final Callback<DatePicker, DateCell> dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            
            /** Method to set specific dates in date picker boxes */
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(newStart)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #c4c3d0;");
                }else if (item.isAfter(maxDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #c4c3d0;");
                }
            }
        };
        endDate.setDayCellFactory(dayCellFactory);
    }
}
