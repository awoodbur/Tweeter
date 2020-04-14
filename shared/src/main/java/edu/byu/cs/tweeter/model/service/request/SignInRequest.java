package edu.byu.cs.tweeter.model.service.request;

public class SignInRequest {

    private String alias;
    private String password;

    private SignInRequest() {}

    public SignInRequest(String alias, String password) {
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
