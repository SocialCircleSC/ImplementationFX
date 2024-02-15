import java.util.Scanner;
import java.util.Vector;

import model.Book;
<<<<<<< HEAD
import model.Patron;
=======
import model.BookCollection;
import model.Patron;
import model.PatronCollection;
>>>>>>> 56211d0d20e8ab120a22bfbc5fa436c4c13fa3c5

import java.util.Properties;
import database.*;

public class TestAssign1 {
    public static void main(String[] args) {

        forBook();
<<<<<<< HEAD
        forPatron();

    }

    private static void forPatron() {
        Properties insertProp = new Properties();

        Scanner patronNScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter Patron's name: "); // Ask user for Patron's Name
        String patronName = patronNScanner.nextLine();

        Scanner patronAScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter Patron's address: "); // Ask user for Patron's Address
        String patronAddress = patronAScanner.nextLine();

        Scanner patronCScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron's City: "); // Ask user for Patron's City
        String patronCity = patronCScanner.nextLine();

        Scanner patronSScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron's state code: "); // Ask user for Patron's StateCode
        String patronStateCode = patronSScanner.nextLine();

        Scanner patronZScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron's zipcode: "); // Ask user for Patron's Zipcode
        String patronZipCode = patronZScanner.nextLine();

        Scanner patronEScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron's email: "); // Ask user for Patron's Email
        String patronEmail = patronEScanner.nextLine();

        Scanner patronDScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron's DOB: "); // Ask user for Patron's DOB
        String patronDOB = patronDScanner.nextLine();

        insertProp.setProperty("bookTitle", patronName);
        insertProp.setProperty("author", patronAddress);
        insertProp.setProperty("pubyear", patronCity);
        insertProp.setProperty("pubyear", patronStateCode);
        insertProp.setProperty("pubyear", patronZipCode);
        insertProp.setProperty("pubyear", patronEmail);
        insertProp.setProperty("pubyear", patronDOB);

        Patron insertPatron = new Patron(insertProp);
        insertPatron.save();

        System.out.println("Added book to database");
    }

    public static void forBook() {
=======
        
    }

/*  Insert a new book into the database */
// ==============================================================    
    public static void forBook(){
>>>>>>> 56211d0d20e8ab120a22bfbc5fa436c4c13fa3c5
        Properties insertProp = new Properties();

        Scanner bookTScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter Book Title: "); // Ask user for new book title
        String bookTitle = bookTScanner.nextLine();

        Scanner bookAScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter Book Author: "); // Ask user for new book author
        String bookAuthor = bookAScanner.nextLine();

        Scanner bookPYScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the book's publish year: "); // Ask user for new book pub yeear
        String bookPubYear = bookPYScanner.nextLine();

        insertProp.setProperty("bookTitle", bookTitle);
        insertProp.setProperty("author", bookAuthor);
        insertProp.setProperty("pubyear", bookPubYear);

        Book insertBook = new Book(insertProp);
        insertBook.save();

        System.out.println("Added book to database");
    }

<<<<<<< HEAD
/* Insert a new book into the database */
// ==============================================================
=======
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
>>>>>>> 56211d0d20e8ab120a22bfbc5fa436c4c13fa3c5

/* Insert a new patron into the database */
// ==============================================================

<<<<<<< HEAD
/*
 * Given a part of a title of a book, print all book data for books that match
 * this title
 */
// ==============================================================

/*
 * Given a year, print all book data for books that are published before that
 * year
 */
=======
/* Given a year, print all book data for books that are published before that year */
>>>>>>> 56211d0d20e8ab120a22bfbc5fa436c4c13fa3c5
// ==============================================================

/*
 * Given a date, print all patron data for patrons that are younger than that
 * date
 */
// ==============================================================

/* Given a zip, print all patron data for patrons that live at that zip */
// ==============================================================