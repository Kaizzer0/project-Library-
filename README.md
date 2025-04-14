Object-Oriented Programming 
(CT108H - Spring 2025)
FINAL PROJECT REPORT- LIBRARY MANAGEMENT SYSTEM 

Group Members:
•	Lê Trần Huy Phước 			 B2303896
•	Nguyễn Phúc Thuận Lợi		 B2304067
•	Nguyễn Tấn Đức			 B2303867

Table of Contents
1.	Introduction
1.1. Project Overview
1.2. Objectives
1.3. Scope
2.	Methodology and Approach
2.1. Object-Oriented Principles
2.2. Development Process
3.	System Design and Implementation
3.1. System Architecture
3.2. Core Classes and Relationships
3.3. Data Persistence
3.4. Feature Implementation
3.4.1. User Authentication
3.4.2. User Management (Admin)
3.4.3. Book Management (Librarian)
3.4.4. Borrowing and Returning (Reader)
4.	Tools and Technologies
5.	Conclusion
6.	 Project Structure


1. Introduction
1.1. Project Overview
This project implements a command-line based Library Management System as the final assignment for the Object-Oriented Programming (With Java) course (CT108H). The system allows different types of users (Admin, Librarian, Reader) to interact with the library's resources, managing users, books, and borrowing transactions.
1.2. Objectives
The primary objectives of this project are:
•	Apply fundamental Object-Oriented Programming (OOP) principles (Encapsulation, Inheritance, Polymorphism, Abstraction) in Java.
•	Design and implement a functional system simulating library operations.
•	Practice file handling in Java for basic data persistence.
•	Develop a console-based application with role-based access control.
1.3. Scope
The scope of this project includes:
•	User Roles: Admin, Librarian, and Reader, each with specific permissions.
•	User Management: Admins can add, remove, and list users.
•	Book Management: Librarians can add (Printed Books, EBooks), remove, and list all books.
•	Library Operations: Readers can view available books, borrow printed books, and return borrowed books.
•	Authentication: Users must log in with a username and password.
•	Data Persistence: User, book, and basic transaction data are stored in text files (CSV).

2. Methodology and Approach
2.1. Object-Oriented Principles
The design and implementation heavily rely on OOP principles:
•	Abstraction: The User class is declared abstract, defining common attributes (username, password, role) and methods (getUsername, getPassword, getRole, toFileString, toString) for all user types. It cannot be instantiated directly. Similarly, the Book class acts as a blueprint for different book types.
•	Encapsulation: Class attributes (e.g., username in User, title in Book) are kept private (protected in User for subclass access, private in Book and others). Access and modification are controlled through public getter methods (and setters where appropriate, like setAvailabilityStatus, setDueDate). This hides the internal state and protects data integrity.
•	Inheritance:
o	Admin, Librarian, and Reader classes inherit from the User class, reusing common properties and methods while adding role-specific functionalities (e.g., addUser in Admin, addBook in Librarian, borrowBook in Reader).
o	EBook and PrintedBook inherit from the Book class, sharing common book attributes and methods (getTitle, getISBN, etc.) while adding specific attributes (fileFormat for EBook, numberOfPages for PrintedBook) and overriding toFileString for correct file storage.
•	Polymorphism:
o	The LibraryManagementSystem class manages a List<User> and List<Book>. This allows storing objects of different subclasses (Admin, Librarian, Reader in the user list; PrintedBook, EBook in the book list) in the same collection.
o	Method overriding (toString(), toFileString()) allows treating objects of subclasses differently while calling the same method name. For instance, u.toFileString() behaves correctly whether u is an Admin, Librarian, or Reader.
o	The main application loop in LibraryManagementSystem.run() uses the currentUser (which can be Admin, Librarian, or Reader) and checks its role to display the appropriate menu and call role-specific methods.
2.2. Development Process
The project was developed iteratively:
1.	Design: Defined core classes (User, Book) and their relationships (inheritance). Identified required attributes and methods.
2.	Implementation - Core Models: Implemented the base classes (User, Book) and their subclasses (Admin, Librarian, Reader, PrintedBook, EBook).
3.	Implementation - System Logic: Developed the LibraryManagementSystem class to manage users and books, including login logic and core operations.
4.	Implementation - Persistence: Added file I/O functionality to load and save user and book data from/to text files (users.txt, books.txt) and log transactions (transactions.txt).
5.	Implementation - User Interface: Created the command-line interface within the LibraryManagementSystem.run() method and the Main class for application entry.
6.	Testing: Performed manual testing by running the application with different user roles and scenarios (adding/removing users/books, borrowing/returning).

3. System Design and Implementation
3.1. System Architecture
The project follows a simple layered architecture:
•	models Package: Contains the data structures representing the core of the system (User, Book, and their subclasses). These classes also contain some behavior specific to the entity (e.g., borrowBook in Reader).
•	services Package: Contains the main application logic (LibraryManagementSystem). This class acts as a service layer, coordinating operations, managing data collections, handling file persistence, and interacting with the models.
•	Main Class (Default Package): The entry point of the application, responsible for initiating the LibraryManagementSystem.
3.2. Core Classes and Relationships
•	User (abstract): Base class for all users.
o	Admin: Inherits User. Manages users.
o	Librarian: Inherits User. Manages books.
o	Reader: Inherits User. Manages borrowing/returning books.
•	Book: Base class for library items.
o	PrintedBook: Inherits Book. Represents physical books with page numbers. Can be borrowed.
o	EBook: Inherits Book. Represents electronic books with a file format. Cannot be borrowed in this implementation (availability set to false).
•	LibraryManagementSystem: Central class holding List<User> and List<Book>. Handles login, user/book management operations, file I/O, and runs the main application loop. Interacts with User and Book objects.

 
3.3. Data Persistence
•	Mechanism: Plain text files (.txt) are used for storing data.
•	Format: Data is stored in a comma-separated value (CSV) like format.
o	users.txt: username,password,role
o	books.txt: title,author,genre,ISBN,availabilityStatus,dueDate[,extra_info]
	extra_info is fileFormat for EBook.
	extra_info is numberOfPages for PrintedBook.
o	transactions.txt: username,ISBN,action(borrow/return),timestamp (Appended on each action).
•	Implementation: Standard Java I/O classes (BufferedReader, BufferedWriter, FileReader, FileWriter) are used within LibraryManagementSystem (loadUsersFromFile, updateUsersFile, loadBooksFromFile, updateBooksFile, updateTransactionsFile). Data is loaded at startup and saved whenever changes occur (e.g., adding a user, borrowing a book).

3.4. Feature Implementation
3.4.1. User Authentication
•	Solution: The LibraryManagementSystem.login(String username, String password) method iterates through the loaded users list. It compares the provided credentials with the stored username and password for each User object.
•	Process: If a match is found, the corresponding User object is returned. Otherwise, null is returned, indicating invalid credentials. The main loop continues prompting until a valid login occurs.
3.4.2. User Management (Admin)
•	Solution: Implemented within the Admin class (addUser, removeUser, listUsers), which calls methods in LibraryManagementSystem.
•	Process:
o	addUser: Prompts for new user details, checks for existing username using system.getUserByUsername(). If unique, creates the appropriate User subclass (Admin, Librarian, or Reader) and calls system.addUser(), which adds the user to the list and updates users.txt via updateUsersFile().
o	removeUser: Prompts for username, calls system.removeUser(), which uses users.removeIf() to filter the list and then updates users.txt.
o	listUsers: Calls system.listUsers(), which iterates through the users list and prints user information using the overridden toString() method.
3.4.3. Book Management (Librarian)
•	Solution: Implemented within the Librarian class (addBook, removeBook, listBooks), interacting with LibraryManagementSystem.
•	Process:
o	addBook: Prompts for book type (PrintedBook/EBook) and details. Checks for existing ISBN using system.getBookByISBN(). Creates the specific Book subclass (PrintedBook or EBook) and calls system.addBook(), which adds it to the books list and updates books.txt via updateBooksFile().
o	removeBook: Prompts for ISBN, calls system.removeBook(), which uses books.removeIf() and updates books.txt.
o	listBooks: Calls system.listBooks(), iterating and printing book info using toString().
3.4.4. Borrowing and Returning (Reader)
•	Solution: Implemented within the Reader class (borrowBook, returnBook, viewAvailableBooks), interacting with LibraryManagementSystem.
•	Process:
o	borrowBook: Prompts for ISBN. Retrieves the Book using system.getBookByISBN(). Checks if the book exists and isAvailable(). If yes, sets availability to false, calculates and sets a due date (calculateDueDate()), calls system.updateBooksFile() to save the change, and logs the transaction via system.updateTransactionsFile().
o	returnBook: Prompts for ISBN. Retrieves the Book. Checks if it exists and is not available (i.e., was borrowed). Sets availability to true, clears the due date, calls system.updateBooksFile(), and logs the transaction.
o	viewAvailableBooks: Calls system.viewBooks(), which iterates through the books list and prints only those where isAvailable() is true.

4. Tools and Technologies
•	Programming Language: Java (JDK 11 or later recommended)
•	Integrated Development Environment (IDE): Visual Studio Code (or any preferred Java IDE like IntelliJ IDEA, Eclipse)
•	Version Control System: Git / GitHub 
•	Libraries: Standard Java Libraries:
o	java.util.* (for Scanner, List, ArrayList, Calendar)
o	java.io.* (for File, BufferedReader, BufferedWriter, FileReader, FileWriter, IOException)
•	Operating System: Compatible with Windows, macOS, Linux

5. Conclusion
This project successfully implemented a command-line Library Management System using Java and Object-Oriented Programming principles. The system provides core functionalities for managing users and books, handling borrowing/returning operations, and persisting data to text files. Key OOP concepts like Abstraction, Encapsulation, Inheritance, and Polymorphism were applied to create a modular and extensible design. 

	Potential future improvements could include:
•	Adding more detailed transaction logging.
•	Implementing search functionalities for books (by title, author) and users.
•	Adding features like fine calculation for overdue books.

6. Project Structure
Folder Structure:
LibraryManagementSystem/
├── data/                 # Directory for data files
│   ├── users.txt
│   ├── books.txt
│   └── transactions.txt
├── src/                  # Directory for source code
│   ├── model/            # Package for data model classes
│   │   ├── User.java
│   │   ├── Admin.java
│   │   ├── Librarian.java
│   │   ├── Reader.java
│   │   ├── Book.java
│   │   ├── PrintedBook.java
│   │   └── EBook.java
│   ├── service/           # Package for service/logic classes
│   │   └── LibraryManagementSystem.java
│   └── Main.java         # Main application entry point
├── .gitignore            # Git ignore file 

