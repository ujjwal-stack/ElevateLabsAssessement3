import java.time.LocalDateTime;

public class LibraryTransaction {
    private String transactionId;
    private String bookId;
    private String userId;
    private String transactionType; // "ISSUE" or "RETURN"
    private LocalDateTime timestamp;
    private double fine;
    
    public LibraryTransaction(String transactionId, String bookId, String userId, 
                            String transactionType, double fine) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.userId = userId;
        this.transactionType = transactionType;
        this.timestamp = LocalDateTime.now();
        this.fine = fine;
    }
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public String getBookId() { return bookId; }
    public String getUserId() { return userId; }
    public String getTransactionType() { return transactionType; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public double getFine() { return fine; }
    
    @Override
    public String toString() {
        return String.format("Transaction[ID: %s, Type: %s, Book: %s, User: %s, Time: %s, Fine: ₹%.2f]",
                transactionId, transactionType, bookId, userId, 
                timestamp.toString().replace("T", " "), fine);
    }
}