package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

public class SignInResponse extends Response {

    private User user;
    private String token;

    public SignInResponse(String message) {
        super(false, message);
    }

    public SignInResponse(User user, String token) {
        super(true);
        this.user = user;
        this.token = token;
    }

    public User getUser() { return user; }

    public String getToken() {
        return token;
    }
}
