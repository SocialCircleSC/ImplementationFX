import java.util.Scanner;
import java.util.Vector;

import model.Book;
import java.util.Properties;

public class TestAssign1 {
    public static void main(String[] args) {

        forBook();


    }

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
}


/*  Insert a new book into the database */
// ==============================================================

/* Insert a new patron into the database */
// ==============================================================

/* Given a part of a title of a book, print all book data for books that match this title */
// ==============================================================

/* Given a year, print all book data for books that are published before that year */
// ==============================================================

/* Given a date, print all patron data for patrons that are younger than that date */
// ==============================================================

/* Given a zip, print all patron data for patrons that live at that zip */
// ==============================================================