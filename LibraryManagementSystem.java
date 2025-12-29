//Library Management System

import java.util.Scanner;

// The below class represents a single book in the library.
// It stores book details such as book ID, title, author, and issue status.
// This class uses encapsulation by keeping data members private
// and providing methods to issue, return, and display book information.
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean issued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public boolean isIssued() {
        return issued;
    }

    public void issueBook() {
        issued = true;
    }

    public void returnBook() {
        issued = false;
    }

    public void displayBook() {
        System.out.println(
            bookId + " | " + title + " | " + author + " | " +
            (issued ? "Issued" : "Available")
        );
    }
}

// The below class manages the collection of books in the system.
// It provides functionalities to add books, display all books,
// issue books, and return books.
// This class handles the core business logic of the library
// and demonstrates abstraction by hiding internal operations.
class Library {
    private Book[] books;
    private int count;

    public Library() {
        books = new Book[100];
        count = 0;
    }

    public void addBook(Book book) {
        books[count++] = book;
        System.out.println("Book added successfully!");
    }

    public void displayBooks() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nBook ID | Title | Author | Status");
        for (int i = 0; i < count; i++) {
            books[i].displayBook();
        }
    }

    public void issueBook(int bookId) {
        for (int i = 0; i < count; i++) {
            if (books[i].getBookId() == bookId) {
                if (!books[i].isIssued()) {
                    books[i].issueBook();
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book already issued!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void returnBook(int bookId) {
        for (int i = 0; i < count; i++) {
            if (books[i].getBookId() == bookId) {
                if (books[i].isIssued()) {
                    books[i].returnBook();
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book was not issued!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }
}

// The below class contains the main method.
// It acts as the entry point of the application and handles
// user interaction through a menu-driven console interface.
// This class connects user inputs with the Library class methods.
public class LibraryManagementSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    Book book = new Book(id, title, author);
                    library.addBook(book);
                    break;

                case 2:
                    library.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    library.issueBook(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    library.returnBook(sc.nextInt());
                    break;

                case 5:
                    System.out.println("Exiting Library System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
