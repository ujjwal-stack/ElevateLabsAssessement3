public class Faculty extends User {
    private String department;
    private String designation;
    
    public Faculty(String userId, String name, String email, String phone, 
                  String department, String designation) {
        super(userId, name, email, phone, 5); // Faculty can borrow max 5 books
        this.department = department;
        this.designation = designation;
    }
    
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    
    public void setDepartment(String department) { this.department = department; }
    public void setDesignation(String designation) { this.designation = designation; }
    
    @Override
    public String getUserType() {
        return "Faculty";
    }
    
    @Override
    public String toString() {
        return String.format("Faculty[ID: %s, Name: %s, Dept: %s, Designation: %s, Books: %d/%d]",
                userId, name, department, designation, getCurrentBooksCount(), maxBooksAllowed);
    }
}
