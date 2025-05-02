 # Library Management System

---

## Table of Contents
1. [Introduction](#introduction)  
   1. [Project Overview](#project-overview)  
   2. [Objectives](#objectives)  
   3. [Scope](#scope)  
2. [Methodology and Approach](#methodology-and-approach)  
   1. [Object-Oriented Principles](#object-oriented-principles)  
   2. [Development Process](#development-process)  
3. [System Design and Implementation](#system-design-and-implementation)  
   1. [System Architecture](#system-architecture)  
   2. [Core Classes and Relationships](#core-classes-and-relationships)  
   3. [Data Persistence](#data-persistence)  
   4. [Feature Implementation](#feature-implementation)  
      1. [User Authentication](#user-authentication)  
      2. [User Management (Admin)](#user-management-admin)  
      3. [Book Management (Librarian)](#book-management-librarian)  
      4. [Borrowing and Returning (Reader)](#borrowing-and-returning-reader)  
4. [Tools and Technologies](#tools-and-technologies)  
5. [Conclusion](#conclusion)  

---

## 1. Introduction

### 1.1. Project Overview
This project implements a command-line Library Management System , it allows different user roles (Admin, Librarian, Reader) to manage the library’s resources, including users, books, and borrowing transactions.

### 1.2. Objectives
- Apply core OOP principles (Encapsulation, Inheritance, Polymorphism, Abstraction) in Java.  
- Design and develop a functional system simulating real-world library operations.  
- Practice file I/O in Java for basic data persistence.  
- Build a console-based application with role-based access control.

### 1.3. Scope
- **User Roles:** Admin, Librarian, Reader.  
- **User Management:** Admin can add, remove, and list users.  
- **Book Management:** Librarian can add (PrintedBook, EBook), remove, and list books.  
- **Library Operations:** Reader can view available books, borrow printed books, and return them.  
- **Authentication:** Username and password login.  
- **Data Persistence:** All data stored in CSV-style text files.

---

## 2. Methodology and Approach

### 2.1. Object-Oriented Principles
- **Abstraction:** Abstract classes `User` and `Book` define shared attributes and methods.  
- **Encapsulation:** Private/protected fields with public getters/setters.  
- **Inheritance:**  
  - `Admin`, `Librarian`, `Reader` extend `User`.  
  - `PrintedBook`, `EBook` extend `Book`.  
- **Polymorphism:**  
  - Store mixed subclasses in `List<User>` and `List<Book>`.  
  - Override `toString()` and `toFileString()` for uniform handling.

### 2.2. Development Process
1. **Design:** Define core classes and their relationships.  
2. **Implementation – Models:** Code `User`, `Book` and subclasses.  
3. **Implementation – Logic:** Build `LibraryManagementSystem` for operations and I/O.  
4. **Persistence:** Add file handling (users.txt, books.txt, transactions.txt).  
5. **Interface:** Create console UI in `LibraryManagementSystem.run()`.  
6. **Testing:** Manual tests across all user roles and scenarios.

---

## 3. System Design and Implementation

### 3.1. System Architecture
- **models /** – Data classes (`User`, `Book`, subclasses).  
- **services /** – Business logic (`LibraryManagementSystem`).  
- **Main.java** – Application entry point.

### 3.2. Core Classes and Relationships
- **Abstract `User`**  
  - `Admin`  
  - `Librarian`  
  - `Reader`  
- **Abstract `Book`**  
  - `PrintedBook`  
  - `EBook`  
- **`LibraryManagementSystem`** – Manages `List<User>`, `List<Book>`, login, I/O, and UI.

### 3.3. Data Persistence
- CSV files:  
  - `users.txt`: `username,password,role`  
  - `books.txt`: `title,author,genre,ISBN,availabilityStatus,dueDate[,extraInfo]`  
  - `transactions.txt`: `username,ISBN,action,timestamp`

### 3.4. Feature Implementation

#### 3.4.1. User Authentication
Login compares credentials against loaded users and returns the matching `User` or `null`.

#### 3.4.2. User Management (Admin)
- **addUser():** Check for duplicate username, add to list, update users.txt.  
- **removeUser():** Remove by username, update file.  
- **listUsers():** Print user info.

#### 3.4.3. Book Management (Librarian)
- **addBook():** Choose type, verify ISBN, add, update books.txt.  
- **removeBook():** Remove by ISBN, update file.  
- **listBooks():** Print all books.

#### 3.4.4. Borrowing and Returning (Reader)
- **borrowBook():** Check availability, set unavailable, calculate due date, log transaction.  
- **returnBook():** Set available, clear due date, log transaction.  
- **viewAvailableBooks():** Display books where `isAvailable == true`.

---

## 4. Tools and Technologies
- **Language:** Java (JDK 11+)  
- **IDE:** VS Code / IntelliJ IDEA / Eclipse  
- **Version Control:** Git & GitHub  
- **Libraries:** `java.util.*`, `java.io.*`  
- **OS:** Windows, macOS, Linux  

---

## 5. Conclusion
This project delivers a console-based Library Management System demonstrating key OOP concepts. Future enhancements could include overdue fines, advanced search, and a GUI or web interface.

---
