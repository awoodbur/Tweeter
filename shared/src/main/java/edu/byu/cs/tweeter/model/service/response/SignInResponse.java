package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

public class SignInResponse extends Response {

    private User user;

    public SignInResponse(String message) {
        super(false, message);
    }

    public SignInResponse(User user) {
        super(true);
        this.user = user;
    }

    public User getUser() { return user; }
}
