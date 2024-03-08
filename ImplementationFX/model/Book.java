// specify the package
package model;

// system imports
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFrame;

// project imports
import exception.InvalidPrimaryKeyException;
import database.*;

import impresario.IView;

import userinterface.View;
import userinterface.ViewFactory;

public class Book extends EntityBase implements IView {

    private static final String myTableName = "Book";

    protected Properties dependencies;

    // GUI Components

    private String updateStatusMessage = "";

    // Constructor
    public Book(String bookId) throws InvalidPrimaryKeyException {
        super(myTableName);

        setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (BookId = " + bookId + ")";

        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        // You must get one book at least
        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();

            // There should be EXACTLY one book. More than that is an error
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple books matching id : "
                        + bookId + " found.");
            } else {
                // copy all the retrieved data into persistent state
                Properties retrievedBookData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();

                Enumeration allKeys = retrievedBookData.propertyNames();
                while (allKeys.hasMoreElements() == true) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retrievedBookData.getProperty(nextKey);
                    // BookNumber = Integer.parseInt(retrievedBookData.getProperty("bookNumber"));

                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }

            }
        }
        // If no Book found for this user name, throw an exception
        else {
            throw new InvalidPrimaryKeyException("No book matching id : "
                    + bookId + " found.");
        }
    }

    // Constructor to initialize empty Book object
    public Book()
    {
        super(myTableName);
        setDependencies();
        persistentState = new Properties();
    }

    // Constructor to initialize Book object with given properties
    public Book(Properties props) {
        super(myTableName);

        setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements() == true) {
            String nextKey = (String) allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);

            if (nextValue != null) {
                persistentState.setProperty(nextKey, nextValue);
            }
        }
    }

    // DO we need the above constructor if we have this function? This is code repetition...
    public void processNewBook(Properties props)
    {
        setDependencies();
        
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements() == true)
        {
            String nextKey = (String) allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);

            if (nextValue != null) {
                persistentState.setProperty(nextKey, nextValue);
            }
        }
    }

    /**
	 * This method is needed solely to enable the Book information to be
	 * displayable in a table NOTE: order is important, this gets the data from each table row
	 *
	 */
	// --------------------------------------------------------------------------
	public Vector<String> getEntryListView() {
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("bookId"));
		v.addElement(persistentState.getProperty("bookTitle"));
		v.addElement(persistentState.getProperty("author"));
		v.addElement(persistentState.getProperty("pubyear"));
        v.addElement(persistentState.getProperty("status"));

		return v;
	}

    public void save() {
        updateStateInDatabase();
    }

    private void updateStateInDatabase() {
        try {
            if (persistentState.getProperty("bookId") != null) {
                // update
                Properties whereClause = new Properties();
                whereClause.setProperty("bookId",
                        persistentState.getProperty("bookId"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "Book data for book number : " + persistentState.getProperty("bookId")
                        + " updated successfully in database!";
            } else {
                // insert
                Integer bookNumber = insertAutoIncrementalPersistentState(mySchema, persistentState);
                persistentState.setProperty("bookId", "" + bookNumber.intValue());
                updateStatusMessage = "Book data for new book : " + persistentState.getProperty("bookId")
                        + "installed successfully in database!";
            }
        } catch (SQLException ex) {
            updateStatusMessage = "Error in installing book data in database!";
        }
        System.out.println("updateStateInDatabase " + updateStatusMessage);
    }

    /* Return Book information as a string */
    // -----------------------------------------------------------------------------------
    public String toString()
    {
        return "Title: " + persistentState.getProperty("bookTitle") + " Author: " + persistentState.getProperty("author") +
        " Year: " + persistentState.getProperty("pubyear");
    }

    /* Display Book information to user  */
    // -----------------------------------------------------------------------------------
    public void display()
    {
        System.out.println(toString());
    }

    // -----------------------------------------------------------------------------------
    private void setDependencies() {
        dependencies = new Properties();

        myRegistry.setDependencies(dependencies);
    }

    // -----------------------------------------------------------------------------------
    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

    @Override
    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);    }

    @Override
    public String getState(String key) {
        if (key.equals("UpdateStatusMessage") == true)
			return updateStatusMessage;

		return persistentState.getProperty(key);
    }

    @Override
    public void stateChangeRequest(String key, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stateChangeRequest'");
    }
}