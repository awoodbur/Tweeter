package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

public class GetUserResponse extends Response {
    private User user;

    public GetUserResponse(String message) {
        super(false, message);
    }

    public GetUserResponse(User user) {
        super(true);
        this.user = user;
    }

    public User getUser() { return user; }
}
