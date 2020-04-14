package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

public class SignUpResponse extends Response {

    private User user;
    private String token;

    public SignUpResponse(String message) {
        super(false, message);
    }

    public SignUpResponse(User user, String token) {
        super(true);
        this.user = user;
        this.token = token;
    }

    public User getUser() { return user; }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
