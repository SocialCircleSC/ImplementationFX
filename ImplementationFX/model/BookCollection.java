package model;

import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import impresario.IView;
import java.util.Properties;

public class BookCollection extends EntityBase implements IView {

    private static final String myTableName = "Book";
   
    Vector<Book> bookList = new Vector();

    public BookCollection() {
        super(myTableName);
        bookList = new Vector();
    }

    private void executeQueryAndPopulate(String query)
    {
        Vector allDataRetrieved = getSelectQueryResult(query);

        // Check if each book has a date older than given date
        for (int i = 0; i < allDataRetrieved.size(); i++) {
            Properties nextBook = (Properties) allDataRetrieved.elementAt(i);

            // Remeber in the Book class you change the type of the contructor from String
            // to Properties
            Book book = new Book(nextBook);
            bookList.add(book);
        }
    }

    private void findBooksOlderThanDate(String date)  {

        // The query to get all the books
        String query = "SELECT * FROM " + myTableName + " WHERE (pubyear > " + date + ")";
        executeQueryAndPopulate(query);
    }

    private void findBooksNewerThanDate(String date)  {

        // The query to get all the books
        // I think I did this wrong can I just use > instead of = in the query
        // statement?
        String query = "SELECT * FROM " + myTableName + " WHERE (pubyear < " + date + ")";
        executeQueryAndPopulate(query);

    }

    private void findBooksWithTitleLike(String title)  {

        // The query to get all the books
        String query = "SELECT * FROM " + myTableName + " WHERE (bookTitle LIKE '%" + title + "%'')";
        executeQueryAndPopulate(query);

    }

    private void findBooksWithAuthorLike(String title)  {

        // The query to get all the books
        String query = "SELECT * FROM " + myTableName + " WHERE (bookTitle LIKE '%" + title + "%'')";
        executeQueryAndPopulate(query);

    }

    /* Display each Book information from Book in Collection to user */
	// ==============================================================
	public void displayCollection()
	{
        // Cycle through each Patron in the Patron collection
		for (int count = 0; count < bookList.size(); count++)
        {
            System.out.println(bookList.elementAt(count).toString();); // Convert each Book information to a string and display it
        }
	}

    @Override
    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
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

    @Override
    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }

    }
}