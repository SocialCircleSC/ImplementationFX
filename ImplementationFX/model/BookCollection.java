package model;

import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import impresario.IView;
import java.util.Properties;

public class BookCollection extends EntityBase implements IView {

    private static final String myTableName = "Book";
    Vector<Book> olderBooks = new Vector();
    Vector<Book> newerBooks = new Vector();
    Vector<Book> titleLike = new Vector();
    Vector<Book> authorLike = new Vector();

    public BookCollection() {
        super(myTableName);
        Vector<Book> bookList = new Vector();
    }

    private Vector<Book> findBooksOlderThanDate(String date) throws InvalidPrimaryKeyException {

        // The query to get all the books
        String query = "SELECT * FROM " + myTableName + " WHERE (pubyear > " + date + ")";
        Vector allDataRetrieved = getSelectQueryResult(query);

        // Check if each book has a date older than given date
        for (int i = 0; i < allDataRetrieved.size(); i++) {
            Properties nextBook = (Properties) allDataRetrieved.elementAt(i);

            // Remeber in the Book class you change the type of the contructor from String
            // to Properties
            Book book = new Book(nextBook);
            olderBooks.add(book);
        }

        return olderBooks;

    }

    private Vector<Book> findBooksNewerThanDate(String date) throws InvalidPrimaryKeyException {

        // The query to get all the books
        //I think I did this wrong can I just use > instead of = in the query statement?
        String query = "SELECT * FROM " + myTableName + " WHERE (pubyear < " + date + ")";
        Vector allDataRetrieved = getSelectQueryResult(query);

        // Check if each book has a date newer than given date
        for (int i = 0; i < allDataRetrieved.size(); i++) {
            Properties nextBook = (Properties) allDataRetrieved.elementAt(i);

            // Remeber in the Book class you change the type of the contructor from String
            // to Properties
            Book book = new Book(nextBook);
            newerBooks.add(book);

        }

        return newerBooks;

    }

    private Vector<Book> findBooksWithTitleLike(String title) throws InvalidPrimaryKeyException {

        // The query to get all the books
        String query = "SELECT * FROM " + myTableName + " WHERE (bookTitle LIKE " + title + ")";
        Vector allDataRetrieved = getSelectQueryResult(query);

        // Check if each book has a date newer than given date
        for (int i = 0; i < allDataRetrieved.size(); i++) {
            Properties nextBook = (Properties) allDataRetrieved.elementAt(i);

            // Remeber in the Book class you change the type of the contructor from String
            // to Properties
            Book book = new Book(nextBook);
            titleLike.add(book);
        }

        return newerBooks;

    }

    private Vector<Book> findBooksWithAuthorLike(String title) throws InvalidPrimaryKeyException {

        // The query to get all the books
        String query = "SELECT * FROM " + myTableName + " WHERE (bookTitle LIKE " + title + ")";
        Vector allDataRetrieved = getSelectQueryResult(query);

        // Check if each book has a date newer than given date
        for (int i = 0; i < allDataRetrieved.size(); i++) {
            Properties nextBook = (Properties) allDataRetrieved.elementAt(i);

            // Remeber in the Book class you change the type of the contructor from String
            // to Properties
            Book book = new Book(nextBook);
            authorLike.add(book);
        }

        return newerBooks;

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