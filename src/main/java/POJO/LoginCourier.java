package POJO;

public class LoginCourier {

    private String login;
    private String password;
    public LoginCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public LoginCourier(){ //констр.без параметров
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
