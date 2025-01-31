import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private final String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;

    }

    public Book(String title) {
        this.title = title;
    }

    // Getters and setters1
    
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Title: " + title + 
               "\nAuthor: " + author + 
               "\nISBN: " + ISBN + 
               "\nAvailable: " + (isAvailable ? "Yes" : "No") + "\n";
    }
}

class LibraryCatalog {
    private final List<Book> books;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void removeBook(String ISBN) {
        boolean removed = books.removeIf(book -> book.getISBN().equals(ISBN));
        System.out.println(removed ? "Book removed successfully!" : "Book not found!");
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book searchByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the catalog!");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            
            do {
                System.out.println("\nLibrary Catalog System");
                System.out.println("1. Add Book");
                System.out.println("2. Remove Book");
                System.out.println("3. Search by Title");
                System.out.println("4. Search by ISBN");
                System.out.println("5. Display All Books");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter ISBN: ");
                        String ISBN = scanner.nextLine();
                        catalog.addBook(new Book(title, author, ISBN));
                 }
                        
                    case 2 -> {
                        System.out.print("Enter ISBN to remove: ");
                        String removeISBN = scanner.nextLine();
                        catalog.removeBook(removeISBN);
                    }
                        
                    case 3 -> {
                        System.out.print("Enter title to search: ");
                        String searchTitle = scanner.nextLine();
                        List<Book> titleResults = catalog.searchByTitle(searchTitle);
                        if (titleResults.isEmpty()) {
                            System.out.println("No books found with that title!");
                        } else {
                            System.out.println("\nSearch Results:");
                            for (Book book : titleResults) {
                                System.out.println(book);
                            }
                        }
                    }
                        
                    case 4 -> {
                        System.out.print("Enter ISBN to search: ");
                        String searchISBN = scanner.nextLine();
                        Book isbnResult = catalog.searchByISBN(searchISBN);
                        if (isbnResult == null) {
                            System.out.println("No book found with that ISBN!");
                        } else {
                            System.out.println("\nSearch Result:");
                            System.out.println(isbnResult);
                        }
                    }
                        
                    case 5 -> {
                        System.out.println("\nAll Books:");
                        catalog.displayAllBooks();
                    }
                        
                    case 6 -> System.out.println("Exiting...");
                        
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 6);
        }
    }
}