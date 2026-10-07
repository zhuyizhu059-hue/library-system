import java.util.ArrayList;
import java.util.Scanner;

abstract class LibraryItem {
    protected String category;

    public LibraryItem(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public abstract String getDetails();
}

class Book extends LibraryItem {
    private String id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book() {
        super("General");
        this.id = "B-000";
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.isBorrowed = false;
    }

    public Book(String category, String id, String title, String author) {
        super(category);
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    public void borrowBook() {
        if (isBorrowed) {
            System.out.println("Book is already borrowed.");
        } else {
            isBorrowed = true;
            System.out.println("Book borrowed successfully: " + title);
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("Book returned successfully: " + title);
        } else {
            System.out.println("Book is not currently borrowed.");
        }
    }

    @Override
    public String getDetails() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Category: " + category + " | Borrowed: " + isBorrowed;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Book book1 = new Book("Fiction", "B-101", "The Hobbit", "J.R.R. Tolkien");
        Book book2 = new Book("Science", "B-202", "A Brief History of Time", "Stephen Hawking");
        Book book3 = new Book("Fantasy", "B-303", "Harry Potter", "J.K. Rowling");

        ArrayList<Book> catalog = new ArrayList<>();
        catalog.add(book1);
        catalog.add(book2);
        catalog.add(book3);

        int choice = 0;

        while (choice != 4) {
            System.out.println("\n=== Library System Menu ===");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Search Catalog");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();

                if (choice == 1) {
                    System.out.print("Enter book title to borrow: ");
                    String titleToBorrow = input.nextLine();
                    boolean found = false;

                    for (Book book : catalog) {
                        if (book.getTitle().equalsIgnoreCase(titleToBorrow)) {
                            found = true;
                            book.borrowBook();
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Book not found in catalog.");
                    }
                } else if (choice == 2) {
                    System.out.print("Enter book title to return: ");
                    String titleToReturn = input.nextLine();
                    boolean found = false;

                    for (Book book : catalog) {
                        if (book.getTitle().equalsIgnoreCase(titleToReturn)) {
                            found = true;
                            book.returnBook();
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Book not found in catalog.");
                    }
                } else if (choice == 3) {
                    System.out.print("Enter a title to search: ");
                    String searchTitle = input.nextLine();
                    boolean found = false;

                    for (Book book : catalog) {
                        if (book.getTitle().equalsIgnoreCase(searchTitle)) {
                            System.out.println(book.getDetails());
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("No matching book found.");
                    }
                } else if (choice == 4) {
                    System.out.println("Exiting the library system. Goodbye!");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }

        input.close();
    }
}
