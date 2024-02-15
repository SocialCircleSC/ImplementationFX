// Patron Class for pair programming assignment
// specify the package
package model;

// system imports
import java.sql.SQLException; // for sql errors / error info
import java.util.Enumeration; // allows for enumerating through data (we use it for our vectors)
import java.util.Properties; // allows to use Properties, which are similar in nature to hashmaps w/ key:value pairs
import java.util.Vector; // vectors

// project imports
import exception.InvalidPrimaryKeyException;
import impresario.IView;
import database.*;

public class Patron extends EntityBase implements IView {
	private static final String myTableName = "Patron"; // Set table name for db
	protected Properties dependencies;
	private String updateStatusMessage = ""; // For GUI

	// Constructor
	// from patronId in database
	// ----------------------------------------------------------
	public Patron(String patronId)
			throws InvalidPrimaryKeyException {
		super(myTableName);
		setDependencies();

		// Get row from Patron table w/ matching Id
		String query = "SELECT * FROM " + myTableName + " WHERE (patronId = " + patronId + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query); // get attributes from patron table as
																			// "properties"

		// You must get one account at least ie if we received some data from patron
		// table
		if (allDataRetrieved != null) {
			int size = allDataRetrieved.size(); // get size of data received ie how many patron accs w/ matching id

			// There should be EXACTLY one account. More than that is an error
			if (size != 1) {
				throw new InvalidPrimaryKeyException("Multiple patrons matching id : " + patronId + " found.");
			}

			else {
				// copy all the retrieved data into persistent state
				// populating persistentState with data from patron table aka constructing
				// Patron from that data
				Properties retrievedPatronData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedPatronData.propertyNames(); // get keys/fields
				while (allKeys.hasMoreElements() == true) {
					// Grab key value pairs from patron table data
					String nextKey = (String) allKeys.nextElement();
					String nextValue = retrievedPatronData.getProperty(nextKey);

					if (nextValue != null) {
						// add keyvalue pairs to persistent state
						persistentState.setProperty(nextKey, nextValue);
					}

				} // end while
			}
		} // end if allDataRetrieved

		// If no account matching patronId is found
		else {
			throw new InvalidPrimaryKeyException("No patron matching id: " + patronId + " found.");
		}

	} // end of constructor

	// Constructor
	// Creating new Patron from user inputed data via Properties
	// ----------------------------------------------------------
	public Patron(Properties props) {
		super(myTableName);
		setDependencies();

		persistentState = new Properties();

		Enumeration allKeys = props.propertyNames(); // get keys/fields
		while (allKeys.hasMoreElements() == true) {
			// Grab key value pairs from Properties
			String nextKey = (String) allKeys.nextElement();
			String nextValue = props.getProperty(nextKey);

			if (nextValue != null) {
				// add keyvaluepairs to persistent state
				persistentState.setProperty(nextKey, nextValue);
			}
		}

	}

	/*
	 * these classes should have an update method
	 * that either inserts a new object into the database
	 * or updates an existing object in the database
	 */
	// -----------------------------------------------------------------------------------
	public void save() {
		updateStateInDatabase(); // public update method calls private update method
	}

	private void updateStateInDatabase() {
		try {
			if (persistentState.getProperty("patronId") != null) // we are updating an existing patrons data
			{
				Properties whereClause = new Properties();
				whereClause.setProperty("patronId", persistentState.getProperty("patronId")); // specify which field for
																								// query
				persistentState.getProperty("patronId"); // get data to save from persistent state
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Patron data for patronId: " + persistentState.getProperty("patronId")
						+ " successfully updated in database!";
			} else // "saving" new data, inserting new row to table
			{
				Integer patronId = insertAutoIncrementalPersistentState(mySchema, persistentState); // patronId is																					// dbms
				persistentState.setProperty("patronId", "" + patronId.intValue());
				updateStatusMessage = "Patron data for new patronId: " + persistentState.getProperty("patronId")
						+ " successfully saved in database!";
			}
		} catch (SQLException ex) // provide error info if we run into sql trouble
		{
			updateStatusMessage = "Error in updating patron data in database";
		}
	}

	/* Return Patron information as a String */
	// ==============================================================
	public String toString()
	{
		return persistentState.getProperty("patronId") + " Name: " + persistentState.getProperty("name") +
		" Email: " + persistentState.getProperty("email") + " Status: " + persistentState.getProperty("status");
	} // end of toString

	/* Display Patron information to user */
	// ==============================================================
	public void display()
	{
		System.out.println(toString());
	}

	// -----------------------------------------------------------------------------------
	private void setDependencies() {
		dependencies = new Properties();

		myRegistry.setDependencies(dependencies);
	}

	// note: all classes inheriting from entityBase must include this
	// -----------------------------------------------------------------------------------
	protected void initializeSchema(String tableName) {
		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateState'");
	}

	@Override
	public Object getState(String key) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getState'");
	}

	@Override
	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'stateChangeRequest'");
	}
}
