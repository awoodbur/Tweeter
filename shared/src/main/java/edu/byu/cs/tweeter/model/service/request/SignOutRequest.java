package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class SignOutRequest {

    private User user;

    public SignOutRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
