public class User {
    private char[] password;
    private String username;

    public User(char[] password, String username) {
        this.password = password;
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
