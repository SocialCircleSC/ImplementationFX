// PatronCollection Class for pair programming assignment
// This should hold a Vector of ‘Patron’ objects

// specify the package
package model;

// system imports
import java.util.Properties;
import java.util.Vector;

// project imports
import exception.InvalidPrimaryKeyException;
import event.Event;
import database.*;

/** The class containing the PatronCollection for the BookKeeping application */
//==============================================================
public class patronCollection extends EntityBase
{
    private static final String myTableName = "Patron";
    private Vector<Patron> patrons; // Define a vector of patron objects, this will be our collection

    // Consctructor for PatronCollection class
    public PatronCollection() throws Exception
    {
        super(myTableName);

        Vector<Patron> patronList = new Vector<Patron>();
        
    }

    /**Use a query SELECT * FROM Patron WHERE dateOfBirth >= date (our string date)*/
//==============================================================
public Vector<Patron> findPatronsOlderThan(String date)
{
    //Give an error if the date given is empty / null (?)

    // Get patrons from database with dates > given date
    String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth > " + date + ")";
    Vector<Properties> allDataRetreived = getSelectQueryResult(query); // get data from query into a vector

    // We want to get at least one patron or we'll inform that there are no patrons whose date is in given constraint
    if (allDataRetreived != null)
    {
        patrons = new Vector<Patron>(); // Instantiate new vector of patrons to use

        for (int count = 0; count < allDataRetreived.size(); count++) // Cycle through each row found by our query
        {
            Properties nextPatronData = (Properties)allDataRetreived.elementAt(count); // get individual properties object aka a row

            Patron patron = new Patron(nextPatronData); // create a Patron from row received
            if (patron != null) // if we were able to recieve a patron
            {
                patrons.add(patron); // Add patron to our patron collection
            } // end if

        } // end count for
    } // end if allDataRetrieved
    
    return patrons;
} // end findPatronsOlderTHan

} // end of patronCollection class



 // DO we need this?
    /** Funtion to add a Patron to our Patron collection */
    //==============================================================
