package hu.gyulbro.webservice.duplicate;

public class User {

    private String nickname;
    private String firstName;
    private String lastName;
    private String userID;
    private String googleID;
    private String email;
    private String password;

    public User(String nickname, String firstName, String lastName, String userID,
                String googleID, String email, String password) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.googleID = googleID;
        this.email = email;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
