package projeto1.appmitologia.model;

public class User {
    private String userName;
    private String password;

    public User() {}

    public User(String userName, String password) {
        this.password = password;
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public String getUserName() {
        return userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}

