package apptschedule;

import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.Language;
import utilities.UCertifyDB;
import utilities.UCertifyDBDefault;
import view_controller.LoginController;


/** ApptSchedule Class creates and opens an application for scheduling. */
public class ApptSchedule extends Application {
  
    /** Initiates app login window. 
     * @param stage Sets the stage
     * @throws java.lang.Exception */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view_controller/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        // Set user location and language on login screen
        LoginController log = loader.getController();
        log.setLocationLabel();
        log.setLoginLanguage();
        
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        if(Language.getUserLocale().getLanguage().equals("fr")) {
            stage.setTitle(Language.stageFr());
        }
        else {
            stage.setTitle("Appointment Management System");
        }
        stage.show();
    } 

    /** Main method to start the application. 
     * @param args Gets arguments
     * @throws java.sql.SQLException */
    public static void main(String[] args) throws SQLException {
        
        // start DB connection and set statement using returned Connection
        UCertifyDBDefault.setStatement(UCertifyDB.connectDB()); // set Statement
        
        launch(args);
        
        // close database connection
        UCertifyDB.closeDB();
    }
}
