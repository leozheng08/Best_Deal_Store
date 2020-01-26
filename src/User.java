import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8259590407715072561L;
    private int id;
    private String username;
    private String password;
    private String userType;

    public User(String username, String password, String userType)

    {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
