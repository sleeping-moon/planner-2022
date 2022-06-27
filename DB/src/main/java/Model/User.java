package Model;

public class User {
    private int userId;
    private String userName;
    private String userLogin;
    private String userPhoto;
    private String userPassword;
    private int userRole;
    private int userActive;

    public User() {
    }

    public User(String name, String login, String password) {
        this.userName = name;
        this.userLogin = login;
        this.userPassword = password;
    }

    public int getUserActive() {
        return userActive;
    }

    public void setUserActive(int userActive) {
        this.userActive = userActive;
    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getuserLogin() {
        return userLogin;
    }

    public void setuserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getuserPassword() {
        return userPassword;
    }

    public void setuserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getuserRole() {
        return userRole;
    }

    public void setuserRole(int userRole) {
        this.userRole = userRole;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}
