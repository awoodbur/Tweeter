package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class SignOutRequest extends Request {

    private User user;

    private SignOutRequest() {
        super("<token>");
    }

    public SignOutRequest(User user, String token) {
        super(token);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
