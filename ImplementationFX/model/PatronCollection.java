// PatronCollection Class for pair programming assignment
// This should hold a Vector of ‘Patron’ objects

// specify the package
package model;

// system imports
import java.util.Properties;
import java.util.Vector;

// project imports
import exception.InvalidPrimaryKeyException;
import database.*;

/** The class containing the PatronCollection for the BookKeeping application */
//==============================================================
public class patronCollection extends entityBase
{
    private static final String myTableName = "Patron";
    private Vector<Patron> patrons; // Create a vector of patron objects, this is our collection

    // Consctructor for PatronCollection class
    public PatronCollection() throws Exception
    {
        super(myTableName);
        patronList = new Vector();
        
    }
}