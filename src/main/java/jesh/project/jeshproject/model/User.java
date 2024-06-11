package jesh.project.jeshproject.model;

public class User {
    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private String username;
    private String password;
    private int id;

    /**
     * Constructor with id.
     * This constructor is typically used when dealing with existing users
     * who already have an id (e.g., fetched from the database).
     */
    public User(int id, String firstName, String lastName, String birthday, String email, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor without id.
     * This constructor is typically used when creating new users before they
     * are inserted into the database and do not yet have an id.
     */
    public User(String firstName, String lastName, String birthday, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getter for firstName
    public String getFirstName() {
        return firstName;
    }

    // Setter for firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter for lastName
    public String getLastName() {
        return lastName;
    }

    // Setter for lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter for birthday
    public String getBirthday() {
        return birthday;
    }

    // Setter for birthday
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for id
    public int getId() {
        return this.id;
    }
}
