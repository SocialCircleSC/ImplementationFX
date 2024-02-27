// specify the package

// system imports
import java.util.Locale;
import java.util.ResourceBundle;
import java.io.FileOutputStream;
import java.io.File;

import javafx.application.Application; //need this for javafx
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// project imports
import event.Event;
import event.EventLog;
import common.PropertyFile;
import model.Librarian;
import model.Teller;
import userinterface.MainStageContainer;
import userinterface.WindowPosition;

public class InterfaceTest extends Application {

    private Librarian currentLib;

    /** Main frame of the application */
    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("This is the program for pair assignment 2");

        // Create the top-level container (main frame) and add contents to it.
        MainStageContainer.setStage(primaryStage, "Library System");
        mainStage = MainStageContainer.getInstance();

        // Finish setting up the stage (ENABLE THE GUI TO BE CLOSED USING THE TOP RIGHT
        // 'X' IN THE WINDOW), and show it.
        mainStage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent event) {
                System.exit(0);
            }
        });

        try {
            currentLib = new Librarian();
        } catch (Exception exc) {
            System.err.println("InterfaceTest.java - could not create Teller!");
            new Event(Event.getLeafLevelClassName(this), "ATM.<init>", "Unable to create Teller object", Event.ERROR);
            exc.printStackTrace();
        }

        WindowPosition.placeCenter(mainStage);

        mainStage.show();

    }

    /**
     * The "main" entry point for the application. Carries out actions to
     * set up the application
     * Must be in class extending Application to usue javafx, must have this as main
     * method for any javafx app
     */
    // ----------------------------------------------------------
    public static void main(String[] args) {

        launch(args);
    }

}
