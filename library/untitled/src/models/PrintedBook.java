package models;

public class PrintedBook extends Book {
    private int numberOfPages;

    public PrintedBook(String title, String author, String genre, String ISBN, int numberOfPages) {
        super(title, author, genre, ISBN);
        this.numberOfPages = numberOfPages;
    }
    
    public int getNumberOfPages() { return numberOfPages; }
    public void setNumberOfPages(int numberOfPages) { this.numberOfPages = numberOfPages; }
    
    @Override
    public String toFileString() {
        return super.toFileString() + "," + numberOfPages;
    }
}
