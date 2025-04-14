package models;

public class Book {
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private boolean availabilityStatus;
    private String dueDate;

    public Book(String title, String author, String genre, String ISBN) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.availabilityStatus = true; // Sách có sẵn mặc định
    }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    
    public boolean isAvailable() { return availabilityStatus; }
    public void setAvailabilityStatus(boolean availabilityStatus) { this.availabilityStatus = availabilityStatus; }
    
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    
    // Dạng chuỗi dùng ghi file
    public String toFileString() {
        return title + "," + author + "," + genre + "," + ISBN + "," +
               (availabilityStatus ? "true" : "false") + "," +
               (dueDate != null ? dueDate : "null");
    }
    
    @Override
    public String toString() {
        return title + " by " + author;
    }
}
