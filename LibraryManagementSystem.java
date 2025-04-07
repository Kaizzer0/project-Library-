abstract class User {
    protected String username;
    protected String password;
    protected String role;
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public String getUsername() {
         return username; 
        
        }
    public String getPassword() {
         return password; 
        }
    public String getRole() {
         return role; 
        }
    
    public String toCSV() {
        return username + "," + password + "," + role;
    }
}
class Admin extends User {
    public Admin(String username, String password) {
        super(username, password, "Admin");
    }
}

class Librarian extends User {
    public Librarian(String username, String password) {
        super(username, password, "Librarian");
    }
}

class Reader extends User {
    public Reader(String username, String password) {
        super(username, password, "Reader");
    }
}