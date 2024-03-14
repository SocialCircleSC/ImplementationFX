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
import model.Patron;
import model.PatronCollection;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class PatronCollectionView extends View{
    protected TableView<PatronTableModel> tableOfPatrons;
    protected Button cancelButton;
	protected Button submitButton;

	protected MessageView statusLog;

    public PatronCollectionView(IModel model)
    {
        super(model, "PatronCollectionView");

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

        // show each Patron information in a table

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

		tableOfPatrons = new TableView<PatronTableModel>();
		tableOfPatrons.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		TableColumn patronNumberColumn = new TableColumn("Patron Id") ;
		patronNumberColumn.setMinWidth(100);
		patronNumberColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("patronId"));
		
		TableColumn patronNameColumn = new TableColumn("Patron Name") ;
		patronNameColumn.setMinWidth(100);
		patronNameColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("patronName"));
		
		TableColumn patronAddressColumn = new TableColumn("Address") ;
		patronAddressColumn.setMinWidth(100);
		patronAddressColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("patronAddress"));
		
		TableColumn patronCityColumn = new TableColumn("City") ;
		patronCityColumn.setMinWidth(100);
		patronCityColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("patronCity"));

		
		TableColumn patronStateCodeColumn = new TableColumn("State Code");
		patronStateCodeColumn.setMinWidth(100);
		patronStateCodeColumn.setCellValueFactory(
			new PropertyValueFactory<PatronTableModel, String>("patronStateCode"));

        TableColumn patronZipColumn = new TableColumn("Zip");
		patronZipColumn.setMinWidth(100);
		patronZipColumn.setCellValueFactory(
			new PropertyValueFactory<PatronTableModel, String>("patronZip"));   
        
        TableColumn patronEmailColumn = new TableColumn("Email");
		patronEmailColumn.setMinWidth(100);
		patronEmailColumn.setCellValueFactory(
			new PropertyValueFactory<PatronTableModel, String>("patronEmail"));   
            
        TableColumn patronDateOfBirthColumn = new TableColumn("Date of Birth");
		patronDateOfBirthColumn.setMinWidth(100);
		patronDateOfBirthColumn.setCellValueFactory(
			new PropertyValueFactory<PatronTableModel, String>("patrondateOfBirth"));
            
        TableColumn patronStatusColumn = new TableColumn("Status");
		patronStatusColumn.setMinWidth(100);
		patronStatusColumn.setCellValueFactory(
			new PropertyValueFactory<PatronTableModel, String>("patronStatus"));    
		
		tableOfPatrons.getColumns().addAll(patronNumberColumn, 
				patronNameColumn, patronAddressColumn, patronCityColumn, patronStateCodeColumn, patronZipColumn, patronEmailColumn, patronDateOfBirthColumn, patronStatusColumn);

		tableOfPatrons.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event)
			{
				if (event.isPrimaryButtonDown() && event.getClickCount() >=2 ){
					
				}
			}
		});

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(150, 150);
		scrollPane.setContent(tableOfPatrons);

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
	 protected void getEntryTableModelValues()
	 {
		 
		 ObservableList<PatronTableModel> tableData = FXCollections.observableArrayList(); // this must be used
		 try
		 {
			 PatronCollection patronList = (PatronCollection)myModel.getState("PatronList"); // need to implement getState for these
 
			  Vector entryList = (Vector)patronList.getState("Patrons"); // implement getState
			 Enumeration entries = entryList.elements();
 
			 while (entries.hasMoreElements() == true)
			 {
				 Patron nextPatron = (Patron)entries.nextElement();
				 Vector<String> view = nextPatron.getEntryListView();
 
				 // add this list entry to the list
				 PatronTableModel nextTableRowData = new PatronTableModel(view);
				 tableData.add(nextTableRowData);
				 
			 }
	
			 tableOfPatrons.setItems(tableData);
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
