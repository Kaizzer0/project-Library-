package models;

import java.util.Scanner;
import services.LibraryManagementSystem;

public class Librarian extends User {
    private static final Scanner scanner = new Scanner(System.in);

    public Librarian(String username, String password) {
        super(username, password, "Librarian");
    }
    
    // Thêm sách (không cho phép trùng ISBN)
    public void addBook(LibraryManagementSystem system) {
        System.out.print("Book Type (PrintedBook/EBook): ");
        String type = scanner.nextLine().trim();
        while (type.equals("")){
            System.out.print("Please enter type:");
            type = scanner.nextLine().trim();
        }
        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim();
        while (title.equals("")){
            System.out.print("Please enter title:");
            title = scanner.nextLine().trim();
        }
        System.out.print("Enter author: ");
        String author = scanner.nextLine().trim();
        while (author.equals("")){
            System.out.print("Please enter author:");
            author = scanner.nextLine().trim();
        }
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine().trim();
        while (genre.equals("")){
            System.out.print("Please enter genra:");
            genre = scanner.nextLine().trim();
        }
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        while (isbn.equals("")){
            System.out.print("Please enter ISBN:");
            isbn = scanner.nextLine().trim();
        }
        if(system.getBookByISBN(isbn) != null) {
            System.out.println("A book with this ISBN already exists!");
            return;
        }
        
        if(type.equalsIgnoreCase("printedbook")) {
            System.out.print("Enter number of pages: ");
            int pages = Integer.parseInt(scanner.nextLine().trim());
            PrintedBook book = new PrintedBook(title, author, genre, isbn, pages);
            system.addBook(book);
        } else if(type.equalsIgnoreCase("ebook")){
            System.out.print("Enter file format: ");
            String format = scanner.nextLine().trim();
            EBook book = new EBook(title, author, genre, isbn, format);
            system.addBook(book);
        } else {
            System.out.println("Invalid book type!");
            return;
        }
        System.out.println("Book added successfully!");
    }

    public void removeBook(LibraryManagementSystem system) {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbn = scanner.nextLine().trim();
        if(system.getBookByISBN(isbn) == null) {
            System.out.println("Book does not exist!");
            return;
        }
        system.removeBook(isbn);
        System.out.println("Book removed successfully!");
    }
    
    public void listBooks(LibraryManagementSystem system) {
        system.listBooks();
    }
}
