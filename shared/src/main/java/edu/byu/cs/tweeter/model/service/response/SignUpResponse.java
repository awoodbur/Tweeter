package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

public class SignUpResponse extends Response {
    private User user;

    public SignUpResponse(String message) {
        super(false, message);
    }

    public SignUpResponse(User user) {
        super(true);
        this.user = user;
    }

    public User getUser() { return user; }
}
