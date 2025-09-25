public class Student extends User {
    private String studentClass;
    private String rollNumber;
    
    public Student(String userId, String name, String email, String phone, 
                  String studentClass, String rollNumber) {
        super(userId, name, email, phone, 3); // Students can borrow max 3 books
        this.studentClass = studentClass;
        this.rollNumber = rollNumber;
    }
    
    public String getStudentClass() { return studentClass; }
    public String getRollNumber() { return rollNumber; }
    
    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    
    @Override
    public String getUserType() {
        return "Student";
    }
    
    @Override
    public String toString() {
        return String.format("Student[ID: %s, Name: %s, Class: %s, Roll: %s, Books: %d/%d]",
                userId, name, studentClass, rollNumber, getCurrentBooksCount(), maxBooksAllowed);
    }
}
