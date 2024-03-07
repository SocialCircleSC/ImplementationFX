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

public class searchBookView extends View {
    // GUI stuff
    private TextField bTitlePart;
    private Button submitButton;
    private Button doneButton;

    // For showing error message
    private MessageView statusLog;

    public searchBookView(IModel model)
    {
        super(model, "searchBookView");

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

        Text titleText = new Text("       Search Books          ");
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

        Text prompt = new Text("PROVIDE BOOK TITLE");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        // data entry fields
        // Book Title field
        Label bookTitle = new Label("Book Title");
        grid.add(bookTitle, 0, 1);
        bTitlePart = new TextField();
        grid.add(bTitlePart, 1, 1);

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
        grid.add(btnContainer, 1, 2);

        return grid;
    }

    // This method processes events generated from our GUI components.
	// Make the ActionListeners delegate to this method
	//-------------------------------------------------------------
	public void processAction(Event evt)
    {

		clearErrorMessage();
        
        // Get fields info
        String bTitleEntered = bTitlePart.getText();
        //Properties props = new Properties();

        if (bTitleEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a book Title!");
            bTitlePart.requestFocus();
        }

        else
        {
            try {
                myModel.stateChangeRequest("searchBook", bTitleEntered); // call stateChangeRequest to insert a book
                
            }

            catch(Exception ex)
            {
                displayErrorMessage("Failed to search for books!");
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
