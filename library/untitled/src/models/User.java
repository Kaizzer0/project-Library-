package models;

public abstract class User {
    protected String username;
    protected String password;
    protected String role;

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }

    // Chuỗi dùng ghi file
    public String toFileString() {
        return username + "," + password + "," + role;
    }
    
    @Override
    public String toString(){
        return username + " - " + role;
    }
}
