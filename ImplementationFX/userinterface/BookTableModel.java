package userinterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

//==============================================================================
public class BookTableModel
{
	private final SimpleStringProperty bookId;
	private final SimpleStringProperty bookTitle;
	private final SimpleStringProperty author;
	private final SimpleStringProperty pubyear;
    private final SimpleStringProperty bookStatus;

	//----------------------------------------------------------------------------
	public BookTableModel(Vector<String> bookData)
	{
		bookId =  new SimpleStringProperty(bookData.elementAt(0));
		bookTitle =  new SimpleStringProperty(bookData.elementAt(1));
		author =  new SimpleStringProperty(bookData.elementAt(2));
		pubyear =  new SimpleStringProperty(bookData.elementAt(3));
        bookStatus = new SimpleStringProperty(bookData.elementAt(4));
	}

	//----------------------------------------------------------------------------
	public String getBookId() {
        return bookId.get();
    }

	//----------------------------------------------------------------------------
    public void setBookId(String number) {
        bookId.set(number);
    }

    //----------------------------------------------------------------------------
    public String getBookTitle() {
        return bookTitle.get();
    }

    //----------------------------------------------------------------------------
    public void setBookTitle(String title) {
        bookTitle.set(title);
    }

    //----------------------------------------------------------------------------
    public String getAuthor() {
        return author.get();
    }

    //----------------------------------------------------------------------------
    public void setAuthor(String bookAuthor) {
        author.set(bookAuthor);
    }
    
    //----------------------------------------------------------------------------
    public String getPubyear() {
        return pubyear.get();
    }

    //----------------------------------------------------------------------------
    public void setPubyear(String bookPubYear)
    {
    	pubyear.set(bookPubYear);
    }

    //----------------------------------------------------------------------------
    public String getBookStatus()
    {
        return bookStatus.get();
    }

    //----------------------------------------------------------------------------
    public void setBookStatus(String bookStatusVal)
    {
        bookStatus.set(bookStatusVal);
    }    

}
