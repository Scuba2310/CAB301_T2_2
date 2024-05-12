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
     * This method will never be called until the sign-in page has verified the user can be added to the database
     * @param birthday DD/MM/YYYY format
     * @param email chars@chars.chars format
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName; }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

//    public void updateDB() {
//        // get all user information and edit db entry
//    }
}