package models;

import java.util.Scanner;
import services.LibraryManagementSystem;

public class Admin extends User {
    private static final Scanner scanner = new Scanner(System.in);

    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    // Thêm user mới (không cho phép trùng username)
    public void addUser(LibraryManagementSystem system) {
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine().trim();
        while (newUsername.equals("")){
            System.out.print("Please enter user name:");
            newUsername = scanner.nextLine().trim();
        }
        if(system.getUserByUsername(newUsername) != null) {
            System.out.println("The user already exists!");
            return;
        }
        System.out.print("Enter password: ");
        String newPassword = scanner.nextLine().trim();
        while (newPassword.equals("")){
            System.out.print("Please enter password:");
            newPassword = scanner.nextLine().trim();
        }
        System.out.print("Enter role (Admin/Librarian/Reader): ");
        String newRole = scanner.nextLine().trim();
        while (newRole.equals("")){
            System.out.print("Please enter role:");
            newRole = scanner.nextLine().trim();
        }
        User newUser;
        switch(newRole.toLowerCase()){
            case "admin":
                newUser = new Admin(newUsername, newPassword);
                break;
            case "librarian":
                newUser = new Librarian(newUsername, newPassword);
                break;
            case "reader":
                newUser = new Reader(newUsername, newPassword);
                break;
            default:
                System.out.println("Invalid role!");
                return;
        }
        system.addUser(newUser);
        System.out.println("User added successfully!");
    }

    public void removeUser(LibraryManagementSystem system) {
        System.out.print("Enter username to remove: ");
        String username = scanner.nextLine().trim();
        if(system.getUserByUsername(username) == null) {
            System.out.println("User does not exist!");
            return;
        }
        system.removeUser(username);
        System.out.println("User removed successfully!");
    }
    
    public void listUsers(LibraryManagementSystem system) {
        system.listUsers();
    }
}
