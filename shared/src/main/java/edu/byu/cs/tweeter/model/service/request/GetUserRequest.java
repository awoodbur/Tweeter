package edu.byu.cs.tweeter.model.service.request;

public class GetUserRequest {

    private String alias;
    private String token;

    private GetUserRequest() {}

    public GetUserRequest(String alias, String token) {
        this.alias = alias;
        this.token = token;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
