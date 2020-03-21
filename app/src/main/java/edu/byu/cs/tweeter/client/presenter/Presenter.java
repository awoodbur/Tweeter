package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.UserServiceProxy;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.UserService;

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
        UserService service = new UserServiceProxy();
        return service.getCurrentUser();
    }

    public User getDisplayUser() {
        UserService service = new UserServiceProxy();
        return service.getDisplayUser();
    }

    public User getUserByAlias(String alias) {
        UserService service = new UserServiceProxy();
        return service.getUserByAlias(alias);
    }

    public boolean doesUserFollowUser(User user1, User user2) {
        UserService service = new UserServiceProxy();
        return service.doesUserFollowUser(user1, user2);
    }

    public void setCurrentUser(User user) {
        UserService service = new UserServiceProxy();
        service.setCurrentUser(user);
    }

    public void setDisplayUser(User user) {
        UserService service = new UserServiceProxy();
        service.setDisplayUser(user);
    }
}
