import java.util.*;

public class LibraryManagementSystemMain {

    private Library library;
    private Scanner scanner;

    public LibraryManagementSystemMain() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    public static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public void start() {
        displayWelcomeMessage();
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleBookManagement();
                    break;
                case 2:
                    handleUserManagement();
                    break;
                case 3:
                    handleIssueBook();
                    break;
                case 4:
                    handleReturnBook();
                    break;
                case 5:
                    handleSearchAndReports();
                    break;
                case 6:
                    library.displayStatistics();
                    break;
                case 7: {
                    System.out.println("\nThank you for using Library Management System! ");
                    System.out.println("Developed by: Ujjwal Singh");
                    System.out.println("Contact: ujjwalrajput1006@gmail.com");
                    return;
                }
                default:
                    System.out.println(" Invalid choice! Please try again.");
            }
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("\n" + repeat("=", 50));
        System.out.println("\n  WELCOME TO LIBRARY MANAGEMENT SYSTEM");
        System.out.println("\n" + repeat("=", 50));
    }

    private void displayMainMenu() {
        System.out.println("\n" + repeat("=", 50));
        System.out.println("                   MAIN MENU");
        System.out.println( repeat("=", 50));
        System.out.println("  1. Book Management");
        System.out.println("  2. User Management");
        System.out.println("  3. Issue Book");
        System.out.println("  4. Return Book");
        System.out.println("  5. Search & Reports");
        System.out.println("  6. Statistics");
        System.out.println("  7. Exit");
        System.out.println("\n" + repeat("=", 50));
    }

    private void handleBookManagement() {
        while (true) {
            System.out.println("\n" + repeat("=", 40));
            System.out.println("         BOOK MANAGEMENT");
            System.out.println( repeat("=", 40));
            System.out.println("1. Add New Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Back to Main Menu");
            
            int choice = getIntInput("\nEnter your choice: ");

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    library.displayAllBooks();
                    break;
                case 4: {
                    return;
                }
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addNewBook() {
        System.out.println("\n" + repeat("=", 20));
        System.out.println("ADD NEW BOOK");
        System.out.println( repeat("=", 20));

        String bookId = getStringInput("Enter Book ID: ");
        String title = getStringInput("Enter Title: ");
        String author = getStringInput("Enter Author: ");
        String isbn = getStringInput("Enter ISBN: ");
        String category = getStringInput("Enter Category: ");

        Book book = new Book(bookId, title, author, isbn, category);

        if (library.addBook(book)) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Book with this ID already exists!");
        }
    }

    private void removeBook() {
        System.out.println("\n" + repeat("=", 20));
        System.out.println("REMOVE BOOK");
        System.out.println(repeat("=", 20));

        String bookId = getStringInput("Enter Book ID to remove: ");

        if (library.removeBook(bookId)) {
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found or is currently issued!");
        }
    }

    private void handleUserManagement() {
        while (true) {
            System.out.println("\n" + repeat("=", 40));
            System.out.println("         USER MANAGEMENT");
            System.out.println( repeat("=", 40));
            System.out.println("1. Add New Student");
            System.out.println("2. Add New Faculty");
            System.out.println("3. Remove User");
            System.out.println("4. View All Users");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    addNewFaculty();
                    break;
                case 3:
                    removeUser();
                    break;
                case 4:
                    library.displayAllUsers();
                    break;
                case 5: {
                    return;
                }
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addNewStudent() {
        System.out.println("\n" + repeat("=", 20));
        System.out.println("ADD NEW STUDENT");
        System.out.println( repeat("=", 20));
        String userId = getStringInput("Enter Student ID: ");
        String name = getStringInput("Enter Name: ");
        String email = getStringInput("Enter Email: ");
        String phone = getStringInput("Enter Phone: ");
        String studentClass = getStringInput("Enter Class: ");
        String rollNumber = getStringInput("Enter Roll Number: ");

        Student student = new Student(userId, name, email, phone, studentClass, rollNumber);

        if (library.addUser(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Student with this ID already exists!");
        }
    }

    private void addNewFaculty() {
        System.out.println("\n" + repeat("=", 20));
        System.out.println("ADD NEW FACULTY");
        System.out.println( repeat("=", 20));

        String userId = getStringInput("Enter Faculty ID: ");
        String name = getStringInput("Enter Name: ");
        String email = getStringInput("Enter Email: ");
        String phone = getStringInput("Enter Phone: ");
        String department = getStringInput("Enter Department: ");
        String designation = getStringInput("Enter Designation: ");

        Faculty faculty = new Faculty(userId, name, email, phone, department, designation);

        if (library.addUser(faculty)) {
            System.out.println("Faculty added successfully!");
        } else {
            System.out.println("Faculty with this ID already exists!");
        }
    }

    private void removeUser() {
        System.out.println("\n" + repeat("=", 20));
        System.out.println("REMOVE USER");
        System.out.println( repeat("=", 20));

        String userId = getStringInput("Enter User ID to remove: ");

        if (library.removeUser(userId)) {
            System.out.println("User removed successfully!");
        } else {
            System.out.println("User not found or has issued books!");
        }
    }

    private void handleIssueBook() {
        System.out.println("\n" + repeat("=", 20));
        System.out.println("ISSUE BOOK");
        System.out.println( repeat("=", 20));

        String bookId = getStringInput("Enter Book ID: ");
        String userId = getStringInput("Enter User ID: ");

        String result = library.issueBook(bookId, userId);
        System.out
                .println(result.startsWith("Book") && result.contains("successfully") ? "Yes " + result : "No " + result);
    }

    private void handleReturnBook() {
        System.out.println("\n" + repeat("=", 20));
        System.out.println("RETURN BOOK");
        System.out.println( repeat("=", 20));

        String bookId = getStringInput("Enter Book ID: ");
        String userId = getStringInput("Enter User ID: ");

        String result = library.returnBook(bookId, userId);
        System.out
                .println(result.startsWith("Book") && result.contains("successfully") ? "Yes " + result : "No " + result);
    }

    private void handleSearchAndReports() {
        while (true) {
            System.out.println("\n" + repeat("=", 40));
            System.out.println("       SEARCH & REPORTS");
            System.out.println( repeat("=", 40));
            System.out.println("1. Search Books");
            System.out.println("2. View Available Books");
            System.out.println("3. View Books by Category");
            System.out.println("4. View Overdue Books");
            System.out.println("5. View Transaction History");
            System.out.println("6. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    searchBooks();
                    break;
                case 2:
                    displayAvailableBooks();
                    break;
                case 3:
                    displayBooksByCategory();
                    break;
                case 4:
                    displayOverdueBooks();
                    break;
                case 5:
                    displayTransactionHistory();
                    break;
                case 6: {
                    return;
                }
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void searchBooks() {
        System.out.println("\n" + repeat("=", 20));
        System.out.println("SEARCH BOOKS");
        System.out.println( repeat("=", 20));

        String query = getStringInput("Enter search query (title/author/category): ");
        List<Book> results = library.searchBooks(query);

        if (results.isEmpty()) {
            System.out.println("No books found matching your query!");
        } else {
            System.out.println("Search Results:");
            results.forEach(System.out::println);
        }
    }

    private void displayAvailableBooks() {
        List<Book> availableBooks = library.getAvailableBooks();

        if (availableBooks.isEmpty()) {
            System.out.println("No books available for issue!");
        } else {
            System.out.println("\n" + repeat("=", 40));
            System.out.println("AVAILABLE BOOKS:");
            System.out.println( repeat("=", 40));
            availableBooks.forEach(System.out::println);
        }
    }

    private void displayBooksByCategory() {
        String category = getStringInput("Enter category: ");
        List<Book> books = library.getBooksByCategory(category);

        if (books.isEmpty()) {
            System.out.println("No books found in this category!");
        } else {
            System.out.println("Books in category '" + category + "':");
            books.forEach(System.out::println);
        }
    }

    private void displayOverdueBooks() {
        List<Book> overdueBooks = library.getOverdueBooks();

        if (overdueBooks.isEmpty()) {
            System.out.println("No overdue books!");
        } else {
            System.out.println("\n" + repeat("=", 50));
            System.out.println("OVERDUE BOOKS:");
            System.out.println( repeat("=", 50));
            overdueBooks.forEach(book -> {
                double fine = book.calculateFine(5.0);
                System.out.println(book + " | Fine: ₹" + fine);
            });
        }
    }

    private void displayTransactionHistory() {
        List<LibraryTransaction> transactions = library.getTransactionHistory();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found!");
        } else {
            System.out.println("\n" + repeat("=", 60));
            System.out.println("TRANSACTION HISTORY:");
            System.out.println( repeat("=", 60));
            transactions.forEach(System.out::println);
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    public static void main(String[] args) {

        try {
            LibraryManagementSystemMain obj = new LibraryManagementSystemMain();
            obj.start();
        } catch (Exception e) {
            System.err.println("System Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
