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

public class BookView extends View {



    // GUI stuff
    private TextField bAuthor;
    private TextField bTitle;
    private TextField bPubYear;
    private TextField insertPatron;
    private Button submitButton;
    private Button doneButton;
    private ComboBox<String> statusBox;

    // For showing error message
    private MessageView statusLog;

    public BookView(IModel model) {
        super(model, "BookView");

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

        Text titleText = new Text("       Insert Book          ");
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

        Text prompt = new Text("BOOK INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        // data entry fields
        // Insert Book Button
        Label bookAuthor = new Label("Book Author");
        grid.add(bookAuthor, 0, 1);

        bAuthor = new TextField();
        bAuthor.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //processAction(e);
            }
        });
        grid.add(bAuthor, 1, 1);

        // data entry fields
        // Insert Book Title
        Label bookTitle = new Label("Book Title");
        grid.add(bookTitle, 0, 2);

        bTitle = new TextField();

        bTitle.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });
        grid.add(bTitle, 1, 2);

        // data entry fields
        // Insert Book Publishing Year
        Label bookPubYear = new Label("Book Publishing Year");
        grid.add(bookPubYear, 0, 3);

        bPubYear = new TextField();

        bPubYear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });
        grid.add(bPubYear, 1, 3);

        statusBox = new ComboBox<String>();
        statusBox.getItems().addAll("ACTIVE", "INACTIVE"); 
        statusBox.setValue("ACTIVE");

        HBox cbContainer = new HBox(10);
        cbContainer.setAlignment(Pos.CENTER);
        cbContainer.getChildren().add(statusBox);
        grid.add(cbContainer, 0, 4);

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
            }
        });

        HBox btnContainer = new HBox(10);
        btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
        btnContainer.getChildren().add(submitButton);
        btnContainer.getChildren().add(doneButton);
        grid.add(btnContainer, 1, 5);

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
        String bAuthorEntered = bAuthor.getText();
        String bTitleEntered = bTitle.getText();
        String bPubYearEntered = bPubYear.getText();
        String statusEntered = statusBox.getValue();
        Properties props = new Properties();
        
        // Check all fields should be populated
        if (bAuthorEntered == null) // Author field should not be empty
        {
            displayErrorMessage("Please enter a book Author!");
			bAuthor.requestFocus();
        }
        else if (bTitleEntered == null) // Title field should not be empty
        {
            displayErrorMessage("Please enter a book Title!");
            bTitle.requestFocus();
        }
        else if (Integer.parseInt(bPubYearEntered) > 2024 || Integer.parseInt(bPubYearEntered) < 1800) // Publication year should be between 1800 and 2024
        {
            displayErrorMessage("Please enter a Publication year between 1800-2024!");
            bPubYear.requestFocus();
        }
        else if (statusEntered == null)
        {
            displayErrorMessage("Status should not be null!");
            statusBox.requestFocus();
        }
        else
        {
            // Add fields to a property object for inserting
            props.setProperty("bookTitle", bTitleEntered);
            props.setProperty("author", bAuthorEntered);
            props.setProperty("pubyear", bPubYearEntered);
            props.setProperty("status", statusEntered);

            try {
                myModel.stateChangeRequest("insertBook", props); // call stateChangeRequest to insert a book
                displaySuccessMessage("Successfully inserted a new book!");
            }

            catch(Exception ex)
            {
                displayErrorMessage("Failed to insert a new Book!");
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
