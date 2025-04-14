package services;

import java.util.*;
import java.io.*;
import models.*;

public class LibraryManagementSystem {
    private List<User> users;
    private List<Book> books;
    
    // Đường dẫn file (chỉnh sửa lại nếu cần)
    private final String usersFile = "data/users.txt";
    private final String booksFile = "data/books.txt";
    private final String transactionsFile = "data/transactions.txt";
    
    public LibraryManagementSystem() {
        users = new ArrayList<>();
        books = new ArrayList<>();
        loadUsersFromFile();
        loadBooksFromFile();
    }
    
  
    public static void run() {
        LibraryManagementSystem system = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);
        models.User currentUser = null;
        
        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
        while (currentUser == null) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            currentUser = system.login(username, password);
            if(currentUser == null) {
                System.out.println("Invalid credentials. Please try again.\n");
            }
        }
        System.out.println("Login successful as: " + currentUser.getRole());
        
        boolean running = true;
        while(running) {
            // Dựa vào vai trò, gọi menu tương ứng
            switch(currentUser.getRole().toLowerCase()){
                case "admin":
                    models.Admin admin = (models.Admin) currentUser;
                    System.out.println("\n---- ADMIN MENU ----");
                    System.out.println("1. Add User");
                    System.out.println("2. Remove User");
                    System.out.println("3. List Users");
                    System.out.println("0. Logout");
                    System.out.print("Select: ");
                    int adminChoice = Integer.parseInt(scanner.nextLine().trim());
                    if(adminChoice == 0) { running = false; break; }
                    if(adminChoice == 1) { admin.addUser(system); }
                    else if(adminChoice == 2) { admin.removeUser(system); }
                    else if(adminChoice == 3) { admin.listUsers(system); }
                    else { System.out.println("Invalid choice!"); }
                    break;
                    
                case "librarian":
                    models.Librarian librarian = (models.Librarian) currentUser;
                    System.out.println("\n---- LIBRARIAN MENU ----");
                    System.out.println("1. Add Book");
                    System.out.println("2. Remove Book");
                    System.out.println("3. List Books");
                    System.out.println("0. Logout");
                    System.out.print("Select: ");
                    int libChoice = Integer.parseInt(scanner.nextLine().trim());
                    if(libChoice == 0) { running = false; break; }
                    if(libChoice == 1) { librarian.addBook(system); }
                    else if(libChoice == 2) { librarian.removeBook(system); }
                    else if(libChoice == 3) { librarian.listBooks(system); }
                    else { System.out.println("Invalid choice!"); }
                    break;
                    
                case "reader":
                    models.Reader reader = (models.Reader) currentUser;
                    System.out.println("\n---- READER MENU ----");
                    System.out.println("1. Borrow Book");
                    System.out.println("2. Return Book");
                    System.out.println("3. View Available Books");
                    System.out.println("0. Logout");
                    System.out.print("Select: ");
                    int readerChoice = Integer.parseInt(scanner.nextLine().trim());
                    if(readerChoice == 0) { running = false; break; }
                    if(readerChoice == 1) { reader.borrowBook(system); }
                    else if(readerChoice == 2) { reader.returnBook(system); }
                    else if(readerChoice == 3) { reader.viewAvailableBooks(system); }
                    else { System.out.println("Invalid choice!"); }
                    break;
                    
                default:
                    System.out.println("Invalid role!");
                    running = false;
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }
    
    // Đăng nhập, trả về User nếu đúng
    public User login(String username, String password) {
        for(User u : users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }
    
    public User getUserByUsername(String username) {
        for(User u : users)
            if(u.getUsername().equals(username))
                return u;
        return null;
    }
    
    public void addUser(User user) {
        if(getUserByUsername(user.getUsername()) != null) {
            System.out.println("Username already exists!");
            return;
        }
        users.add(user);
        updateUsersFile();
    }
    
    public void removeUser(String username) {
        users.removeIf(u -> u.getUsername().equals(username));
        updateUsersFile();
    }
    
    public void listUsers() {
        System.out.println("Users:");
        for(User u : users) {
            System.out.println(u);
        }
    }
    
    private void loadUsersFromFile() {
        File file = new File(usersFile);
        if(!file.exists()) return;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Mỗi dòng: username,password,role
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 3) {
                    String uname = parts[0], pass = parts[1], role = parts[2];
                    User user = null;
                    switch(role.toLowerCase()){ 
                        case "admin":
                            user = new Admin(uname, pass);
                            break;
                        case "librarian":
                            user = new Librarian(uname, pass);
                            break;
                        case "reader":
                            user = new models.Reader(uname, pass);
                            break;
                    }
                    if(user != null)
                        users.add(user);
                }
            }
        } catch(IOException e) {
            System.err.println("Error reading users file: " + e.getMessage());
        }
    }
    
    private void updateUsersFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile))) {
            for(User u : users) {
                writer.write(u.toFileString());
                writer.newLine();
            }
        } catch(IOException e) {
            System.err.println("Error writing users file: " + e.getMessage());
        }
    }
    
    public Book getBookByISBN(String isbn) {
        for(Book b : books)
            if(b.getISBN().equals(isbn))
                return b;
        return null;
    }
    
    public void addBook(Book book) {
        if(getBookByISBN(book.getISBN()) != null) {
            System.out.println("A book with this ISBN already exists!");
            return;
        }
        books.add(book);
        updateBooksFile();
    }
    
    public void removeBook(String isbn) {
        books.removeIf(b -> b.getISBN().equals(isbn));
        updateBooksFile();
    }
    
    public void listBooks() {
        System.out.println("Books:");
        for(Book b : books) {
            System.out.println(b);
        }
    }
    
    private void loadBooksFromFile() {
        File file = new File(booksFile);
        if(!file.exists()) return;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Mỗi dòng: title,author,genre,ISBN,availability,dueDate[,extra]
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length >= 6) {
                    String title = parts[0], author = parts[1], genre = parts[2], isbn = parts[3];
                    boolean available = parts[4].equals("true");
                    String dueDate = parts[5].equals("null") ? null : parts[5];
                    // Nếu có extra để xác định EBook hay PrintedBook
                    if(parts.length == 7) {
                        // Giả sử extra = fileFormat cho EBook
                        EBook ebook = new EBook(title, author, genre, isbn, parts[6]);
                        ebook.setAvailabilityStatus(available);
                        ebook.setDueDate(dueDate);
                        books.add(ebook);
                    } else if(parts.length == 8) {
                        // extra = numberOfPages cho PrintedBook
                        int pages = Integer.parseInt(parts[6]);
                        PrintedBook pb = new PrintedBook(title, author, genre, isbn, pages);
                        pb.setAvailabilityStatus(available);
                        pb.setDueDate(dueDate);
                        books.add(pb);
                    } else {
                        Book b = new Book(title, author, genre, isbn);
                        b.setAvailabilityStatus(available);
                        b.setDueDate(dueDate);
                        books.add(b);
                    }
                }
            }
        } catch(IOException e) {
            System.err.println("Error reading books file: " + e.getMessage());
        }
    }
    
    public void updateBooksFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(booksFile))) {
            for(Book b : books) {
                writer.write(b.toFileString());
                writer.newLine();
            }
        } catch(IOException e) {
            System.err.println("Error writing books file: " + e.getMessage());
        }
    }
    
    public void viewBooks() {
        System.out.println("Available Books:");
        for(Book b : books) {
            if(b.isAvailable())
                System.out.println(b);
        }
    }

    public void updateTransactionsFile(String username, String isbn, String action) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsFile, true))) {
            String transaction = username + "," + isbn + "," + action + "," + System.currentTimeMillis();
            writer.write(transaction);
            writer.newLine();
        } catch(IOException e) {
            System.err.println("Error writing transactions file: " + e.getMessage());
        }
    }
}
