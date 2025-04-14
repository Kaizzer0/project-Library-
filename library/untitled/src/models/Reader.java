package models;

import java.util.Scanner;
import services.LibraryManagementSystem;
import java.util.Calendar;

public class Reader extends User {
    private static final Scanner scanner = new Scanner(System.in);

    public Reader(String username, String password) {
        super(username, password, "Reader");
    }
    
    // Mượn sách: kiểm tra trạng thái, cập nhật hạn trả và ghi giao dịch
    public void borrowBook(LibraryManagementSystem system) {
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbn = scanner.nextLine().trim();
        while (isbn.equals("")){
            System.out.print("Please enter ISBN:");
            isbn = scanner.nextLine().trim();
        }
        Book book = system.getBookByISBN(isbn);
        if(book == null) {
            System.out.println("Book does not exist!");
            return;
        }
        if(!book.isAvailable()) {
            System.out.println("Book is not available for borrowing!");
            return;
        }
        book.setAvailabilityStatus(false);
        book.setDueDate(calculateDueDate());
        system.updateBooksFile();
        system.updateTransactionsFile(username, isbn, "borrow");
        System.out.println("Book borrowed successfully! Due date: " + book.getDueDate());
    }
    
    // Trả sách: cập nhật trạng thái sách và ghi giao dịch
    public void returnBook(LibraryManagementSystem system) {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine().trim();
        Book book = system.getBookByISBN(isbn);
        if(book == null) {
            System.out.println("Book does not exist!");
            return;
        }
        if(book.isAvailable()){
            System.out.println("This book was not borrowed!");
            return;
        }
        book.setAvailabilityStatus(true);
        book.setDueDate(null);
        system.updateBooksFile();
        system.updateTransactionsFile(username, isbn, "return");
        System.out.println("Book returned successfully!");
    }
    
    public void viewAvailableBooks(LibraryManagementSystem system) {
        system.viewBooks();
    }
    
    // Tính hạn trả sách (ví dụ: 10 ngày kể từ hôm nay)
    private String calculateDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        return calendar.getTime().toString();
    }
}
