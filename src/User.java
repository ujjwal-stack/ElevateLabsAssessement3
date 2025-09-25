import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String phone;
    protected List<String> issuedBooks;
    protected int maxBooksAllowed;
    
    public User(String userId, String name, String email, String phone, int maxBooksAllowed) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.maxBooksAllowed = maxBooksAllowed;
        this.issuedBooks = new ArrayList<>();
    }
    
    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<String> getIssuedBooks() { return new ArrayList<>(issuedBooks); }
    public int getMaxBooksAllowed() { return maxBooksAllowed; }
    public int getCurrentBooksCount() { return issuedBooks.size(); }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    
    // Abstract method to be implemented by subclasses
    public abstract String getUserType();
    
    // Check if user can borrow more books
    public boolean canBorrowBook() {
        return issuedBooks.size() < maxBooksAllowed;
    }
    
    // Add issued book to user's record
    public boolean addIssuedBook(String bookId) {
        if (canBorrowBook() && !issuedBooks.contains(bookId)) {
            issuedBooks.add(bookId);
            return true;
        }
        return false;
    }
    
    // Remove returned book from user's record
    public boolean removeIssuedBook(String bookId) {
        return issuedBooks.remove(bookId);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(userId, user.userId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
    
    @Override
    public String toString() {
        return String.format("%s[ID: %s, Name: %s, Email: %s, Books: %d/%d]",
                getUserType(), userId, name, email, getCurrentBooksCount(), maxBooksAllowed);
    }
}
