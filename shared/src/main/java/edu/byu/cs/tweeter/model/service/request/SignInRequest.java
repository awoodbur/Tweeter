package edu.byu.cs.tweeter.model.service.request;

public class SignInRequest extends Request {

    private String alias;
    private String password;

    private SignInRequest() {
        super("<token>");
    }

    public SignInRequest(String alias, String password, String token) {
        super(token);
        this.alias = alias;
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
