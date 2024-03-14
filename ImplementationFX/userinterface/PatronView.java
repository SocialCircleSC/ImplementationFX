package userinterface;
import impresario.IModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Properties;

public class PatronView extends View{
    
    // GUI stuff
    private TextField pName;
    private TextField pAddress;
    private TextField pCity;
    private TextField pStateCOde;
    private TextField pZip;
    private TextField pEmail;
    private TextField dateOfBirth;
    private Button submitButton;
    private Button doneButton;
    private ComboBox<String> statusBox;

    // For showing error message
    private MessageView statusLog;

    public PatronView(IModel model)
    {
        super(model, "PatronView");
        // create a container for showing the contents
        VBox container = new VBox(10);

        container.setPadding(new Insets(15, 5, 5, 5));

        // create a Node (Text) for showing the title
        container.getChildren().add(createTitle());

        // create a Node (GridPane) for showing data entry fields
        container.getChildren().add(createFormContents());

        // Error message area
        container.getChildren().add(createStatusLog("                          "));

        getChildren().add(container);

        // populateFields();
    }

    // Create the label (Text) for the title of the screen
    // -------------------------------------------------------------
    private Node createTitle() {

        Text titleText = new Text("       Insert Patron          ");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKBLUE);
        return titleText;
    }

     // Create the main form contents
    // -------------------------------------------------------------
    private GridPane createFormContents() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text prompt = new Text("PATRON INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        // data entry fields
        // Insert Patron Button
        Label patronName = new Label("Patron Name");
        grid.add(patronName, 0, 1);

        pName = new TextField();
        pName.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //processAction(e);
            }
        });
        grid.add(pName, 1, 1);

        // data entry fields
        // Insert Patron Address
        Label patronAddress = new Label("Patron Address");
        grid.add(patronAddress, 0, 2);

        pAddress = new TextField();

        pAddress.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });
        grid.add(pAddress, 1, 2);

        // data entry fields
        // Insert Patron City
        Label patronCity = new Label("Patron City");
        grid.add(patronCity, 0, 3);

        pCity = new TextField();

        pCity.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });
        grid.add(pCity, 1, 3);
        
        // data entry fields
        // Insert Patron State Code
        Label patronStateCode = new Label("Patron State Code");
        grid.add(patronStateCode, 0, 4);

        pStateCOde = new TextField();

        pStateCOde.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });
        grid.add(pStateCOde, 1, 4);

        // data entry fields
        // Insert Patron Zip
        Label patronZip = new Label("Patron Zip");
        grid.add(patronZip, 0, 5);

        pZip = new TextField();

        pZip.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });
        grid.add(pZip, 1, 5);

        // data entry fields
        // Insert Patron Email
        Label patronEmail = new Label("Patron Email");
        grid.add(patronEmail, 0, 6);

        pEmail = new TextField();

        pEmail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });
        grid.add(pEmail, 1, 6);

        // data entry fields
        // Insert Patron City
        Label patronDoB = new Label("Patron Date of Birth");
        grid.add(patronDoB, 0, 7);

        dateOfBirth = new TextField();

        dateOfBirth.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });
        grid.add(dateOfBirth, 1, 7);
        
        statusBox = new ComboBox<String>();
        statusBox.getItems().addAll("ACTIVE", "INACTIVE"); 
        statusBox.setValue("ACTIVE");

        HBox cbContainer = new HBox(10);
        cbContainer.setAlignment(Pos.CENTER);
        cbContainer.getChildren().add(statusBox);
        grid.add(cbContainer, 0, 8);

        // Submit Button
        submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                processAction(e);
            }
        });

        // Done Button
        doneButton = new Button("Done");
        doneButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
                myModel.stateChangeRequest("CancelTransaction", null);
            }
        });

        HBox btnContainer = new HBox(10);
        btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
        btnContainer.getChildren().add(submitButton);
        btnContainer.getChildren().add(doneButton);
        grid.add(btnContainer, 1, 9);

        return grid;
    }

    // This method processes events generated from our GUI components.
	// Make the ActionListeners delegate to this method
	//-------------------------------------------------------------
	public void processAction(Event evt)
	{
		// DEBUG: System.out.println("LibraryView.actionPerformed()");

		clearErrorMessage();

        // Get fields info
        String pNameEntered = pName.getText();
        String pAddressEntered = pAddress.getText();
        String pCityEntered = pCity.getText();
        String pStateCodeEntered = pStateCOde.getText();
        String pZipEntered = pZip.getText();
        String pEmailEntered = pEmail.getText();
        String pDoBEntered = dateOfBirth.getText();
        String statusEntered = statusBox.getValue();
        Properties props = new Properties();
        
        // Check all fields should be populated
        if (pNameEntered == null) // Author field should not be empty
        {
            displayErrorMessage("Please enter a patron Name!");
			pName.requestFocus();
        }
        else if (pAddressEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a patron Address!");
            pAddress.requestFocus();
        }
        else if (pCityEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a patron City!");
            pCity.requestFocus();
        }
        else if (pStateCodeEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a patron State Code!");
            pStateCOde.requestFocus();
        }
        else if (pZipEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a patron Zip Code!");
            pZip.requestFocus();
        }
        else if (pEmailEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a patron Email!");
            pEmail.requestFocus();
        }
        else if (pDoBEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a patron Date of Birth!");
            dateOfBirth.requestFocus();
        }
        else if (checkDate(dateOfBirth.getText().toString()) == false) // Publication year should be between 1800 and 2024
        {
            displayErrorMessage("Please enter a patron Date of Birth between 1920-01-01 to  2006-01-01!");
            dateOfBirth.requestFocus();
        }
        else if (statusEntered == null)
        {
            displayErrorMessage("Status should not be null!");
            statusBox.requestFocus();
        }
        else
        {
            // Add fields to a property object for inserting
            props.setProperty("name", pNameEntered);
            props.setProperty("address", pAddressEntered);
            props.setProperty("city", pCityEntered);
            props.setProperty("stateCode", pStateCodeEntered);
            props.setProperty("zip", pZipEntered);
            props.setProperty("email", pEmailEntered);
            props.setProperty("dateOfBirth", pDoBEntered);
            props.setProperty("status", statusEntered);

            try {
                myModel.stateChangeRequest("insertPatron", props); // call stateChangeRequest to insert a book
                displaySuccessMessage("Successfully inserted a new Patron!");
            }

            catch(Exception ex)
            {
                displayErrorMessage("Failed to insert a new Patron!");
                ex.printStackTrace();
            }
            
            
        }
         
	}

    public boolean checkDate(String date) {
        boolean retVal = false;

        StringBuilder newDate = new StringBuilder(date);
        newDate.deleteCharAt(4);
        newDate.deleteCharAt(6);

        int dateInt = Integer.parseInt(newDate.toString());

        if (dateInt < 19200101 || dateInt > 20060101) {
            retVal = false;
        } else {
            retVal = true;
        }

        return retVal;
    }

     // Create the status log field
    // -------------------------------------------------------------
    private MessageView createStatusLog(String initialMessage) {

        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

    /**
	 * Display success message
	 */
	//----------------------------------------------------------
    public void displaySuccessMessage(String message)
    {
        statusLog.displayMessage(message);
    }

    /**
	 * Display error message
	 */
	//----------------------------------------------------------
	public void displayErrorMessage(String message)
	{
		statusLog.displayErrorMessage(message);
	}

	/**
	 * Clear error message
	 */
	//----------------------------------------------------------
	public void clearErrorMessage()
	{
		statusLog.clearErrorMessage();
	}

    @Override
    public void updateState(String key, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }
}
