// PatronCollection Class for pair programming assignment
// This should hold a Vector of ‘Patron’ objects

// specify the package
package model;

// system imports
import java.util.Properties;
import java.util.Vector;

// project imports
import exception.InvalidPrimaryKeyException;
import impresario.IView;
import event.Event;
import database.*;

/** The class containing the PatronCollection for the BookKeeping application */
// ==============================================================
public class PatronCollection extends EntityBase implements IView {
    private static final String myTableName = "Patron";
    private Vector<Patron> patrons; // Define a vector of patron objects, this will be our collection

    // Consctructor for PatronCollection class
    public PatronCollection() throws Exception {
        super(myTableName);
        patrons = new Vector<Patron>();

    }

    /** */
    // ==============================================================
    private void executeQueryAndPopulate(String query) {
        Vector<Properties> allDataRetreived = getSelectQueryResult(query); // get data from query into a vector

        // We want to get at least one patron or we'll inform that there are no patrons
        // whose date is in given constraint
        if (allDataRetreived != null) {
            patrons = new Vector<Patron>(); // Instantiate new vector of patrons to use

            for (int count = 0; count < allDataRetreived.size(); count++) // Cycle through each row found by our query
            {
                Properties nextPatronData = (Properties) allDataRetreived.elementAt(count); // get individual properties
                                                                                            // object aka a row

                Patron patron = new Patron(nextPatronData); // create a Patron from row received
                if (patron != null) // if we were able to recieve a patron
                {
                    patrons.add(patron); // Add patron to our patron collection
                } // end if

            } // end count for
        } // end if allDataRetrieved
    }

    /**
     * Use a query SELECT * FROM Patron WHERE dateOfBirth >= date (our string date)
     */
    // ==============================================================
    private void findPatronsOlderThan(String date) {
        // Give an error if the date given is empty / null (?)

        // Get patrons from database with dates > given date
        String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth > " + date + ")";
        executeQueryAndPopulate(query);

    } // end findPatronsOlderTHan

    private void findPatronsYoungerThan(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth < " + date + ")";
        executeQueryAndPopulate(query);

    } // end of findPatronsYoungerThan

    private void findPatronsAtZipCode(String zip) {
        String query = "SELECT * FROM " + myTableName + " WHERE (zip = " + zip + ")";
        executeQueryAndPopulate(query);
    } // end of findPatronsAtZipCode

    private void findPatronsWithNameLike(String name) {
        String query = "SELECT * FROM " + myTableName + " WHERE ( name LIKE '%" + name + "%')";
        executeQueryAndPopulate(query);
    } // end of findPatronsWithNameLike


    /* Display each Patron information from Patron in Collection to user */
	// ==============================================================
	public void displayCollection()
	{
        // Cycle through each Patron in the Patron collection
		for (int count = 0; count < patrons.size(); count++)
        {
            System.out.println(patrons.elementAt(count).toString();); // Convert each patron information to a string and display it
        }
	}
    
    public Object getState(String key)
	{
		throw new UnsupportedOperationException("Unimplemented method 'getState'");
	}

    /** Called via the IView relationship */
    // ----------------------------------------------------------
    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
    }

    public void stateChangeRequest(String key, Object value) {
        throw new UnsupportedOperationException("Unimplemented method 'stateChangeRequest'");
    }

    // note: all classes inheriting from entityBase must include this
    // -----------------------------------------------------------------------------------
    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

} // end of patronCollection class

// DO we need this?
/** Funtion to add a Patron to our Patron collection */
// ==============================================================
