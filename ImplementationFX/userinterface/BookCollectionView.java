package userinterface;
// system imports
import javafx.beans.property.SimpleStringProperty;
import impresario.IModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Book;
import model.BookCollection;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class BookCollectionView extends View{


    protected TableView<BookTableModel> tableOfBooks;
	protected Button cancelButton;
	protected Button submitButton;

	protected MessageView statusLog;

    public BookCollectionView(IModel model)
    {
        super(model, "BookCollectionView");

        // create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContents());

		// Error message area
		container.getChildren().add(createStatusLog("                                            "));

		getChildren().add(container);

        populateFields();
    }

    //--------------------------------------------------------------------------
	protected void populateFields()
	{
		getEntryTableModelValues();
	}

    // Create the label (Text) for the title of the screen
    // -------------------------------------------------------------
    private Node createTitle() {


		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);	

        Text titleText = new Text("       Library System          ");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKBLUE);
        return container;

        // show each book information in a table

    }

    private VBox createFormContents()
    {
		VBox vbox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

		Text prompt = new Text("LIST OF BOOKS");
        prompt.setWrappingWidth(350);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

		tableOfBooks = new TableView<BookTableModel>();
		tableOfBooks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		TableColumn bookNumberColumn = new TableColumn("Book Id") ;
		bookNumberColumn.setMinWidth(100);
		bookNumberColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookId"));
		
		TableColumn bookTitleColumn = new TableColumn("Book Title") ;
		bookTitleColumn.setMinWidth(100);
		bookTitleColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookTitle"));
		
		TableColumn bookAuthorColumn = new TableColumn("Book Author") ;
		bookAuthorColumn.setMinWidth(100);
		bookAuthorColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("author"));
		
		TableColumn bookPubYearColumn = new TableColumn("Publication Year") ;
		bookPubYearColumn.setMinWidth(100);
		bookPubYearColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("pubyear"));

		
		TableColumn bookStatusColumn = new TableColumn("Status");
		bookStatusColumn.setMinWidth(100);
		bookStatusColumn.setCellValueFactory(
			new PropertyValueFactory<BookTableModel, String>("bookStatus"));
		
		tableOfBooks.getColumns().addAll(bookNumberColumn, 
				bookTitleColumn, bookAuthorColumn, bookPubYearColumn, bookStatusColumn);

		tableOfBooks.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event)
			{
				if (event.isPrimaryButtonDown() && event.getClickCount() >=2 ){
					
				}
			}
		});

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(150, 150);
		scrollPane.setContent(tableOfBooks);

		cancelButton = new Button("Back");
 		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
					/**
					 * Process the Cancel button.*/
					//----------------------------------------------------------
       		     	clearErrorMessage();
       		     	myModel.stateChangeRequest("CancelBookList", null); 
            	  }
        	});
		
			HBox btnContainer = new HBox(200);
			btnContainer.setAlignment(Pos.CENTER);
			btnContainer.getChildren().add(cancelButton);
			
			vbox.getChildren().add(grid);
			vbox.getChildren().add(scrollPane);
			vbox.getChildren().add(btnContainer);

			return vbox;
    }

	//--------------------------------------------------------------------------
	// Not using this
	protected void processBookSelected()
	{
		BookTableModel selectedItem = tableOfBooks.getSelectionModel().getSelectedItem();
		
		if(selectedItem != null)
		{
			String selectedBookNumber = selectedItem.getBookId();

			myModel.stateChangeRequest("BookSelected", selectedBookNumber); // How to use this?
		}
	}

	 //--------------------------------------------------------------------------
	 protected void getEntryTableModelValues()
	 {
		 
		 ObservableList<BookTableModel> tableData = FXCollections.observableArrayList(); // this must be used
		 try
		 {
			 BookCollection bookList = (BookCollection)myModel.getState("BookList"); // need to implement getState for these
 
			  Vector entryList = (Vector)bookList.getState("Books"); // implement getState
			 Enumeration entries = entryList.elements();
 
			 while (entries.hasMoreElements() == true)
			 {
				 Book nextBook = (Book)entries.nextElement();
				 Vector<String> view = nextBook.getEntryListView();
 
				 // add this list entry to the list
				 BookTableModel nextTableRowData = new BookTableModel(view);
				 tableData.add(nextTableRowData);
				 
			 }
	
			 tableOfBooks.setItems(tableData);
		 }
		 catch (Exception e) {//SQLException e) {
			 // Need to handle this exception
			 System.out.println("Error in table creation: " + e);
			 e.printStackTrace();
		 }
	 }

	//--------------------------------------------------------------------------
	protected MessageView createStatusLog(String initialMessage)
	{
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}


	/**
	 * Display info message
	 */
	//----------------------------------------------------------
	public void displayMessage(String message)
	{
		statusLog.displayMessage(message);
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
