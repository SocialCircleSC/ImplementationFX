import impresario.IModel;
import javafx.event.ActionEvent;
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
import userinterface.MessageView;
import userinterface.View;

public class bookView extends View {

    // GUI stuff
    private TextField bAuthor;
    private TextField bTitle;
    private TextField bPubYear;
    private TextField insertPatron;
    private Button submitButton;

    // For showing error message
    private MessageView statusLog;

    public bookView(IModel model) {
        super(model, "insertBook");

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

        // data entry fields
        // Insert Book Button
        Label bookAuthor = new Label("Book Author");
        grid.add(bookAuthor, 0, 0);

        bAuthor = new TextField();

        bAuthor.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });

        // data entry fields
        // Insert Book Title
        Label bookTitle = new Label("Book Title");
        grid.add(bookTitle, 0, 1);

        bTitle = new TextField();

        bTitle.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });

        // data entry fields
        // Insert Book Publishing Year
        Label bookPubYear = new Label("Book Publishing Year");
        grid.add(bookPubYear, 0, 2);

        bPubYear = new TextField();

        bPubYear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });

        // Submit Button
        submitButton = new Button("Done");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // processAction(e);
            }
        });

        HBox btnContainer = new HBox(10);
        btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
        btnContainer.getChildren().add(submitButton);
        grid.add(btnContainer, 1, 4);

        return grid;
    }

    // Create the status log field
    // -------------------------------------------------------------
    private MessageView createStatusLog(String initialMessage) {

        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

    @Override
    public void updateState(String key, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }

}
