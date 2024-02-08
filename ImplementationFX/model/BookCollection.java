package model;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import impresario.IView;
import java.util.Properties;



public class BookCollection extends EntityBase implements IView{

    private static final String myTableName = "Book";
    Vector<Book> olderBooks = new Vector();
    public BookCollection(){
        super(myTableName);
        Vector<Book> bookList = new Vector();
    }

    private Vector<Book> findBooksOlderThanDate(String date) throws InvalidPrimaryKeyException{
        

        //The query to get all the books
        String query = "SELECT * FROM " + myTableName + " WHERE (pubyear = " + date + ")";
		Vector allDataRetrieved = getSelectQueryResult(query);

        //Check if each book has a date older than given date
        for(int i=0; i < allDataRetrieved.size(); i++){
            Properties nextBook = (Properties)allDataRetrieved.elementAt(i);

            //Remeber in the Book class you change the type of the contructor from String to Properties
            Book book = new Book(nextBook);

            if(Integer.parseInt(book.getState("pubyear")) > Integer.parseInt(date)){
                olderBooks.add(book);
            }
        }

   
        return olderBooks;

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
        if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}

    }
}