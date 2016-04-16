package at.mritter.dezsys11.model;

/**
 * Model class that represents a user for login/registration
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class User {

    private String email, password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
