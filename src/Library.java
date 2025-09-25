import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Map<String, Book> books;
    private Map<String, User> users;
    private List<LibraryTransaction> transactions;
    private int transactionCounter;
    private static final double FINE_PER_DAY = 5.0;
    private static final int DEFAULT_LOAN_PERIOD = 14;

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.transactionCounter = 1;
        initializeSampleData();
    }

    private String repeat(String str, int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
        sb.append(str);
    }
    return sb.toString();
}

    /* public Library(String libraryName, String libraryAddress){
        this.libraryName=libraryName;
        this.libraryAddress=libraryAddress;
        this.books = new HashMap<>();
        this.users= new HashMap<>();
        this.transactions= new ArrayList<>();
        this.finePerDay = 1.0;
    } */
   private void initializeSampleData() {
        // Add sample books
        addBook(new Book("B001", "Java: The Complete Reference", "Herbert Schildt", "978-0071808556", "Programming"));
        addBook(new Book("B002", "Design Patterns", "Gang of Four", "978-0201633612", "Software Engineering"));
        addBook(new Book("B003", "Clean Code", "Robert Martin", "978-0132350884", "Programming"));
        addBook(new Book("B004", "Spring in Action", "Craig Walls", "978-1617294945", "Framework"));
        addBook(new Book("B005", "Effective Java", "Joshua Bloch", "978-0134685991", "Programming"));
        
        // Add sample users
        addUser(new Student("S001", "Alice Johnson", "alice@email.com", "9876543210", "CS-A", "CS001"));
        addUser(new Student("S002", "Bob Smith", "bob@email.com", "9876543211", "CS-B", "CS002"));
        addUser(new Faculty("F001", "Dr. John Doe", "john.doe@college.edu", "9876543220", "Computer Science", "Professor"));
    }
    
    // Book management methods
    public boolean addBook(Book book) {
        if (books.containsKey(book.getBookId())) {
            return false;
        }
        books.put(book.getBookId(), book);
        return true;
    }
    
    public boolean removeBook(String bookId) {
        Book book = books.get(bookId);
        if (book != null && !book.isIssued()) {
            books.remove(bookId);
            return true;
        }
        return false;
    }
    
    public Book findBook(String bookId) {
        return books.get(bookId);
    }
    
    public List<Book> searchBooks(String query) {
    if (query == null || query.isEmpty()) {
        return new ArrayList<>();
    }
    String lowerQuery = query.toLowerCase();
    return books.values().stream()
            .filter(book -> book.getTitle().toLowerCase().contains(lowerQuery) ||
                           book.getAuthor().toLowerCase().contains(lowerQuery) ||
                           book.getCategory().toLowerCase().contains(lowerQuery))
            .collect(Collectors.toList());
}
    
    public List<Book> getAvailableBooks() {
        return books.values().stream()
                .filter(book -> !book.isIssued())
                .collect(Collectors.toList());
    }
    
    public List<Book> getBooksByCategory(String category) {
        return books.values().stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    // User management methods
    public boolean addUser(User user) {
        if (users.containsKey(user.getUserId())) {
            return false;
        }
        users.put(user.getUserId(), user);
        return true;
    }
    
    public boolean removeUser(String userId) {
        User user = users.get(userId);
        if (user != null && user.getCurrentBooksCount() == 0) {
            users.remove(userId);
            return true;
        }
        return false;
    }
    
    public User findUser(String userId) {
        return users.get(userId);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    // Book issue and return methods
    public String issueBook(String bookId, String userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);
        
        if (book == null) return "Book not found!";
        if (user == null) return "User not found!";
        if (book.isIssued()) return "Book is already issued!";
        if (!user.canBorrowBook()) return "User has reached maximum book limit!";
        
        // Issue the book
        book.issueBook(userId, DEFAULT_LOAN_PERIOD);
        user.addIssuedBook(bookId);
        
        // Record transaction
        String transactionId = "T" + String.format("%04d", transactionCounter++);
        transactions.add(new LibraryTransaction(transactionId, bookId, userId, "ISSUE", 0.0));
        
        return String.format("Book '%s' successfully issued to %s. Due date: %s", 
                book.getTitle(), user.getName(), book.getDueDate());
    }
    
    public String returnBook(String bookId, String userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);
        
        if (book == null) return "Book not found!";
        if (user == null) return "User not found!";
        if (!book.isIssued()) return "Book is not issued!";
        if (!book.getIssuedTo().equals(userId)) return "Book was not issued to this user!";
        
        // Calculate fine if overdue
        double fine = book.calculateFine(FINE_PER_DAY);
        
        // Return the book
        book.returnBook();
        user.removeIssuedBook(bookId);
        
        // Record transaction
        String transactionId = "T" + String.format("%04d", transactionCounter++);
        transactions.add(new LibraryTransaction(transactionId, bookId, userId, "RETURN", fine));
        
        String result = String.format("Book '%s' successfully returned by %s.", 
                book.getTitle(), user.getName());
        if (fine > 0) {
            result += String.format(" Fine: ₹%.2f", fine);
        }
        return result;
    }
    
    // Report methods
    public List<Book> getOverdueBooks() {
        return books.values().stream()
                .filter(Book::isOverdue)
                .collect(Collectors.toList());
    }
    
    public List<LibraryTransaction> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }
    
    public Map<String, Integer> getBookStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Total Books", books.size());
        stats.put("Available Books", (int) books.values().stream().filter(book -> !book.isIssued()).count());
        stats.put("Issued Books", (int) books.values().stream().filter(Book::isIssued).count());
        stats.put("Overdue Books", (int) books.values().stream().filter(Book::isOverdue).count());
        return stats;
    }
    
    public Map<String, Integer> getUserStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Total Users", users.size());
        stats.put("Students", (int) users.values().stream().filter(user -> user instanceof Student).count());
        stats.put("Faculty", (int) users.values().stream().filter(user -> user instanceof Faculty).count());
        return stats;
    }
    
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        
        System.out.println("\n" + repeat("=", 50));
        System.out.println("                           ALL BOOKS IN LIBRARY");
        System.out.println( repeat("=", 80));
        
        books.values().forEach(System.out::println);
    }
    
    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered in the library.");
            return;
        }
        
        System.out.println("\n" + repeat("=", 80));
        System.out.println("                           ALL REGISTERED USERS");
        System.out.println( repeat("=", 80));
        
        users.values().forEach(System.out::println);
    }
    
    public void displayStatistics() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("                    LIBRARY STATISTICS");
        System.out.println( repeat("=", 60));
        
        Map<String, Integer> bookStats = getBookStatistics();
        Map<String, Integer> userStats = getUserStatistics();
        
        System.out.println("\nBOOK STATISTICS:");
        bookStats.forEach((key, value) -> System.out.printf("  %-15s: %d%n", key, value));
        
        System.out.println("\nUSER STATISTICS:");
        userStats.forEach((key, value) -> System.out.printf("  %-15s: %d%n", key, value));
        
        System.out.println("\nTRANSACTION STATISTICS:");
        System.out.printf("  %-15s: %d%n", "Total Transactions", transactions.size());
        
        double totalFines = transactions.stream().mapToDouble(LibraryTransaction::getFine).sum();
        System.out.printf("  %-15s: ₹%.2f%n", "Total Fines", totalFines);
    }
}
