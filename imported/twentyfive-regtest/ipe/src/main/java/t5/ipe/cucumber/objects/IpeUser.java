package t5.ipe.cucumber.objects;

public class IpeUser {

    private String login;
    private String password;


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public IpeUser() {

    }

    public IpeUser(String name, String login, String password, String secret, String role) {
        this.login = login;
        this.password = password;

    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }


}
