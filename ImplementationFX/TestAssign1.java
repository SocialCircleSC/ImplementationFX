import java.util.Scanner;
import java.util.Vector;

import model.Book;
import model.BookCollection;

import java.util.Properties;

public class TestAssign1 {
    public static void main(String[] args) {

        forBook();


    }

/*  Insert a new book into the database */
// ==============================================================    
    public static void forBook(){
        Properties insertProp = new Properties();

        Scanner bookTScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Book Title: ");  //Ask user for new book title
        String bookTitle = bookTScanner.nextLine();

        Scanner  bookAScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Book Author: ");  //Ask user for new book author
        String bookAuthor = bookAScanner.nextLine();

        Scanner  bookPYScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the book's publish year: ");  //Ask user for new book pub yeear
        String bookPubYear = bookPYScanner.nextLine();

        insertProp.setProperty("bookTitle", bookTitle);
        insertProp.setProperty("author", bookAuthor);
        insertProp.setProperty("pubyear", bookPubYear);

        Book insertBook = new Book(insertProp);
        insertBook.save();

        System.out.println("Added to database");
    }

    /* Given a part of a title of a book, print all book data for books that match this title */
    // ==============================================================
    public static void getBookFromTitle()
    {
        // Get a book title from user
        Scanner bookTScanner = new Scanner(System.in); // create input scanner
        System.out.println("ENter title of book to search: ");
        String bookTitle = bookTScanner.nextLine();
        BookCollection findBook = new BookCollection();

        // Get a collection of books with similar title and display to user
        findBook.findBooksWithTitleLike(bookTitle);
        System.out.println("Books matching title: '" + bookTitle + "' found: ");
        findBook.displayCollection();
    }
}

/* Insert a new patron into the database */
// ==============================================================

/* Given a year, print all book data for books that are published before that year */
// ==============================================================

/* Given a date, print all patron data for patrons that are younger than that date */
// ==============================================================

/* Given a zip, print all patron data for patrons that live at that zip */
// ==============================================================