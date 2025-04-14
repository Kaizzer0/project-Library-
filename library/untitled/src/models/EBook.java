package models;

public class EBook extends Book {
    private String fileFormat;

    public EBook(String title, String author, String genre, String ISBN, String fileFormat) {
        super(title, author, genre, ISBN);
        this.fileFormat = fileFormat;
        // Giả sử Ebook không được mượn nên đặt trạng thái luôn false
        setAvailabilityStatus(false);
    }
    
    public String getFileFormat() { return fileFormat; }
    public void setFileFormat(String fileFormat) { this.fileFormat = fileFormat; }
    
    @Override
    public String toFileString() {
        return super.toFileString() + "," + fileFormat;
    }
}
