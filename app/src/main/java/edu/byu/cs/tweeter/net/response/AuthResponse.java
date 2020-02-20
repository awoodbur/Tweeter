package edu.byu.cs.tweeter.net.response;

import edu.byu.cs.tweeter.model.domain.User;

public class AuthResponse extends Response {

    private User user;

    public AuthResponse(String message) {
        super(false, message);
    }

    public AuthResponse(User user) {
        super(true);
        this.user = user;
    }

    public User getUser() { return user; }
}
