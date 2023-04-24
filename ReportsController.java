package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Reports;
import utilities.UCertifyDBQuery;

/** ReportsController Class controls report running functions. */
public class ReportsController implements Initializable {
    private static ResultSet rs; // result set reference

    @FXML
    private TextArea reportTextField;
    @FXML
    private Button customerReportButton;
    @FXML
    private Button contactReportButton;
    @FXML
    private Button myReportButton;
    @FXML
    private Button mainMenuButton;

    /** Method to initialize the controller class. 
     * @param url
     * @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    

    /** Method to get customer appointment report. */
    @FXML
    private void customerReportHander(ActionEvent event) throws SQLException {
        reportTextField.clear();
        rs = UCertifyDBQuery.getCustomerTypeReport();
        reportTextField.appendText("Count " + "\t" + " Type " + "\r\n" + "\r\n" );
        
        while(rs.next()) {
            reportTextField.appendText(rs.getString(1) + "\t" + rs.getString(2) +"\r\n" );
        }
        reportTextField.appendText("\r\n" + "Count " + "\t" + " Month " + "\r\n" + "\r\n" );
        reportTextField.appendText(Reports.getApptMonths());        
    }

    /** Method to get contact schedule report. */
    @FXML
    private void contactReportHandler(ActionEvent event) throws SQLException {
        reportTextField.clear();
        rs = UCertifyDBQuery.getContactReport();
        reportTextField.appendText("Apointment ID " + "\t" + " Title " + "\t" + " Type " + "\t" + 
                    " Description " + "\t" + " Start " + "\t" + " End " + "\r\n" + "\r\n" );
               
        while(rs.next()) {
            reportTextField.appendText(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
                    rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\r\n" );
        }
    }

    /** Method to get my designated report. */
    @FXML
    private void myReportHandler(ActionEvent event) throws SQLException {
        reportTextField.clear();
        
        reportTextField.appendText("Number of remote appointments scheduled is: " + Reports.getRemoteAppts());
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
}
