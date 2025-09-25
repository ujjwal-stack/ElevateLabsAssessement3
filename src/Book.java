import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean isIssued;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String issuedTo;
    
    // Constructor
    public Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        this.issueDate = null;
        this.dueDate = null;
        this.issuedTo = null;
    }
    
    // Getters
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public String getCategory() { return category; }
    public boolean isIssued() { return isIssued; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public String getIssuedTo() { return issuedTo; }
    
    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setCategory(String category) { this.category = category; }
    
    // Issue book method
    public boolean issueBook(String userId, int daysToReturn) {
        if (!isIssued) {
            this.isIssued = true;
            this.issuedTo = userId;
            this.issueDate = LocalDate.now();
            this.dueDate = this.issueDate.plusDays(daysToReturn);
            return true;
        }
        return false;
    }
    
    // Return book method
    public boolean returnBook() {
        if (isIssued) {
            this.isIssued = false;
            this.issuedTo = null;
            this.issueDate = null;
            this.dueDate = null;
            return true;
        }
        return false;
    }
    
    // Check if book is overdue
    public boolean isOverdue() {
        return isIssued && LocalDate.now().isAfter(dueDate);
    }
    
    // Calculate fine for overdue book
    public double calculateFine(double finePerDay) {
        if (!isOverdue()) return 0.0;
        long daysOverdue = LocalDate.now().toEpochDay() - dueDate.toEpochDay();
        return daysOverdue * finePerDay;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(bookId, book.bookId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
    
    @Override
    public String toString() {
        String status = isIssued ? "Issued to: " + issuedTo : "Available";
        return String.format("Book[ID: %s, Title: %s, Author: %s, Category: %s, Status: %s]",
                bookId, title, author, category, status);
    }
}
