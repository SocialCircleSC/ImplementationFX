import java.util.Scanner;
import java.util.Vector;

import model.Book;
import model.BookCollection;
import model.Patron;
import model.PatronCollection;

import java.util.Properties;
import database.*;

public class TestAssign1 {
    public static void main(String[] args) {

        // forBook();
        // forPatron();
        // getBookFromTitle();
        // Missing One more function
        getBookFromDate();
        getPatronFromDate(); // Ask about making a query with the dashes. Comapring
        // with them doesnt seem to
        // work
        // getPatronFromZip();

    } // end of main

    /* Insert a new book into the database */
    // ==============================================================
    public static void forBook() {
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

        System.out.println("Added to database");
    } // end of forBook

    /* Insert a new Patron into the database */
    // ==============================================================
    public static void forPatron() {
        Properties insertProp = new Properties();

        Scanner patronNScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter Patron Name: "); // Ask user for new book title
        String patronName = patronNScanner.nextLine();

        Scanner patronAScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter Patron Address: "); // Ask user for new book author
        String patronAddress = patronAScanner.nextLine();

        Scanner patronCScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron City: "); // Ask user for new book pub yeear
        String patronCity = patronCScanner.nextLine();

        Scanner patronSScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron State Code: "); // Ask user for new book pub yeear
        String patronStateCode = patronCScanner.nextLine();

        Scanner patronZScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron Zip: "); // Ask user for new book pub yeear
        String patronZip = patronCScanner.nextLine();

        Scanner patronEScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron Email: "); // Ask user for new book pub yeear
        String patronEmail = patronCScanner.nextLine();

        Scanner patronDScanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the Patron DOB: "); // Ask user for new book pub yeear
        String patronDOB = patronCScanner.nextLine();

        insertProp.setProperty("name", patronName);
        insertProp.setProperty("address", patronAddress);
        insertProp.setProperty("city", patronCity);
        insertProp.setProperty("stateCode", patronStateCode);
        insertProp.setProperty("zip", patronZip);
        insertProp.setProperty("email", patronEmail);
        insertProp.setProperty("dateOfBirth", patronDOB);

        Patron insertPatron = new Patron(insertProp);
        insertPatron.save();

        System.out.println("Added to database");
    } // end of forPatron

    /*
     * Given a part of a title of a book, print all book data for books that match
     * this title
     */
    // ==============================================================
    public static void getBookFromTitle() {
        // Get a book title from user
        Scanner bookTScanner = new Scanner(System.in); // create input scanner
        System.out.println("Enter title of book to search: ");
        String bookTitle = bookTScanner.nextLine();
        BookCollection findBook = new BookCollection();

        // Get a collection of books with similar title and display to user
        findBook.findBooksWithTitleLike(bookTitle);
        System.out.println("Books matching title: '" + bookTitle + "' found: ");
        findBook.displayCollection();
    } // end of getBookFromTitle

    /*
     * Given a date, print all patron data for patrons that are younger than that
     * date
     */
    // ==============================================================
    public static void getPatronFromDate() {
        // Get a Patron birth date from user
        Scanner patronDScanner = new Scanner(System.in);
        System.out.println("Enter date of birth of patron: ");
        String patronDate = patronDScanner.nextLine();
        PatronCollection findPatron = new PatronCollection();

        findPatron.findPatronsYoungerThan(patronDate);
        System.out.println("Patrons younger than: " + patronDate + " found: ");
        findPatron.displayCollection();
    } // end of getPatronFromDate

    /* Given a zip, print all patron data for patrons that live at that zip */
    // ==============================================================
    public static void getPatronFromZip() {
        // get a Patron Zip from user
        Scanner patronZScanner = new Scanner(System.in);
        System.out.println("Enter zip code of Patron: ");
        String patronZip = patronZScanner.nextLine();
        PatronCollection findPatron = new PatronCollection();

        findPatron.findPatronsAtZipCode(patronZip);
        System.out.println("Patrons that live on " + patronZip + " found: ");
        findPatron.displayCollection();
    } // end of getPatronFromZip

    /*
     * Given a date, print book data for books published before given date
     */
    // ==============================================================
    public static void getBookFromDate() {
        // Get a Patron birth date from user
        Scanner bookDScanner = new Scanner(System.in);
        System.out.println("Enter publish date of book: ");
        String bookDate = bookDScanner.nextLine();
        BookCollection findBook = new BookCollection();

        findBook.findBooksOlderThanDate(bookDate);
        System.out.println("Books published before: " + bookDate + " found: ");
        findBook.displayCollection();
    } // end of getBookFromDate

}
