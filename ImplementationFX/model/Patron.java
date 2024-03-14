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


	public Patron()
	{
		super(myTableName);
        setDependencies();
        persistentState = new Properties();
	}

	// Constructor
	// from patronID in database
	// ----------------------------------------------------------
	public Patron(String patronID)
			throws InvalidPrimaryKeyException {
		super(myTableName);
		setDependencies();

		// Get row from Patron table w/ matching Id
		String query = "SELECT * FROM " + myTableName + " WHERE (patronID = " + patronID + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query); // get attributes from patron table as
																			// "properties"

		// You must get one account at least ie if we received some data from patron
		// table
		if (allDataRetrieved != null) {
			int size = allDataRetrieved.size(); // get size of data received ie how many patron accs w/ matching id

			// There should be EXACTLY one account. More than that is an error
			if (size != 1) {
				throw new InvalidPrimaryKeyException("Multiple patrons matching id : " + patronID + " found.");
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

		// If no account matching patronID is found
		else {
			throw new InvalidPrimaryKeyException("No patron matching id: " + patronID + " found.");
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

	// --------------------------------------------------------------------------
	public Vector<String> getEntryListView() {
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("patronID"));
		v.addElement(persistentState.getProperty("name"));
		v.addElement(persistentState.getProperty("address"));
		v.addElement(persistentState.getProperty("city"));
        v.addElement(persistentState.getProperty("stateCode"));
		v.addElement(persistentState.getProperty("zip"));
		v.addElement(persistentState.getProperty("email"));
		v.addElement(persistentState.getProperty("dateOfBirth"));
		v.addElement(persistentState.getProperty("status"));

		return v;
	}

	public void processNewPatron(Properties props)
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
			if (persistentState.getProperty("patronID") != null) // we are updating an existing patrons data
			{
				Properties whereClause = new Properties();
				whereClause.setProperty("patronID", persistentState.getProperty("patronID")); // specify which field for
																								// query
				persistentState.getProperty("patronID"); // get data to save from persistent state
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Patron data for patronID: " + persistentState.getProperty("patronID")
						+ " successfully updated in database!";
			} else // "saving" new data, inserting new row to table
			{
				Integer patronNumber = insertAutoIncrementalPersistentState(mySchema, persistentState); // patronID is
																										// //
				// dbms
				persistentState.setProperty("patronID", "" + patronNumber.intValue());
				updateStatusMessage = "Patron data for new patronID: " + persistentState.getProperty("patronID")
						+ " successfully saved in database!";
			}
		} catch (SQLException ex) // provide error info if we run into sql trouble
		{
			updateStatusMessage = "Error in updating patron data in database";
		}
	}

	/* Return Patron information as a String */
	// ==============================================================
	public String toString() {
		return persistentState.getProperty("patronID") +
				" Name: " + persistentState.getProperty("name") +
				" Address: " + persistentState.getProperty("address") +
				" City: " + persistentState.getProperty("city") +
				" StateCode: " + persistentState.getProperty("stateCode") +
				" Zipcode: " + persistentState.getProperty("zip") +
				" Email: " + persistentState.getProperty("email") +
				" DOB: " + persistentState.getProperty("dateOfBirth") +
				" Status: " + persistentState.getProperty("status");
	} // end of toString

	/* Display Patron information to user */
	// ==============================================================
	public void display() {
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
		stateChangeRequest(key, value);    }	

	@Override
	public Object getState(String key) {
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
