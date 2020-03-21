package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.UserService;

/**
 * A common base class for all presenters in the application.
 */
public abstract class Presenter {

    /**
     * Returns the currently logged in user.
     *
     * @return the user.
     */
    public User getCurrentUser() {
        return UserService.getInstance().getCurrentUser();
    }

    public User getDisplayUser() { return UserService.getInstance().getDisplayUser(); }

    public User getUserByAlias(String alias) {
        return UserService.getInstance().getUserByAlias(alias);
    }

    public boolean doesUserFollowUser(User user1, User user2) {
        return UserService.getInstance().doesUserFollowUser(user1, user2);
    }

    public void setCurrentUser(User user) { UserService.getInstance().setCurrentUser(user); }

    public void setDisplayUser(User user) {
        UserService.getInstance().setDisplayUser(user);
    }
}
