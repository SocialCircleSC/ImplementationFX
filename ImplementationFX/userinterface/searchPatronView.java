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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Properties;

public class searchPatronView extends View {

    // GUI stuff
    private TextField pZip;
    private Button submitButton;
    private Button doneButton;

    // For showing error message
    private MessageView statusLog;

    public searchPatronView(IModel model)
    {
        super(model, "searchPatronView");

        // create a container for showing the contents
        VBox container = new VBox(10);

        // create a Node (Text) for showing the title
        container.getChildren().add(createTitle());

        // create a Node (GridPane) for showing data entry fields
        container.getChildren().add(createFormContents());

        // Error message area
        container.getChildren().add(createStatusLog("                          "));

        getChildren().add(container);
    }
    
    // Create the label (Text) for the title of the screen
    // -------------------------------------------------------------
    private Node createTitle() {

        Text titleText = new Text("       Search Patrons          ");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKBLUE);
        return titleText;
    }

    private GridPane createFormContents()
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text prompt = new Text("PROVIDE PATRON ZIP CODE");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        // data entry fields
        // Book Title field
        Label patronZip = new Label("Patron ZIP");
        grid.add(patronZip, 0, 1);
        pZip = new TextField();
        grid.add(pZip, 1, 1);

        // Submit Button
        submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Submit on searchPatrons");
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
        grid.add(btnContainer, 1, 2);

        return grid;
    }

    // This method processes events generated from our GUI components.
	// Make the ActionListeners delegate to this method
	//-------------------------------------------------------------
	public void processAction(Event evt)
    {

		clearErrorMessage();
        System.out.println("On processAction for searchPatrons");
        
        // Get fields info
        String pZipEntered = pZip.getText();
        //Properties props = new Properties();

        if (pZipEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a Patron Zip Code!");
            pZip.requestFocus();
        }

        else
        {
            try {
                System.out.println("Launching searchPatrons");
                myModel.stateChangeRequest("searchPatron", pZipEntered); // call stateChangeRequest to search a book
                System.out.println("Successful launch searchPatrons");
            }

            catch(Exception ex)
            {
                displayErrorMessage("Failed to search for Patrons!");
                ex.printStackTrace();
            }
        }
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
