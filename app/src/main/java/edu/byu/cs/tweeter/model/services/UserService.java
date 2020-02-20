package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.request.AuthRequest;
import edu.byu.cs.tweeter.net.response.AuthResponse;

/**
 * Contains the business logic for login and sign up.
 */
public class UserService {

    /**
     * The singleton instance.
     */
    private static UserService instance;

    /**
     * Communication with server.
     */
    private final ServerFacade serverFacade;

    /**
     * The logged in user.
     */
    private User currentUser;

    /**
     * The displayed user.
     */
    private User displayUser;

    /**
     * Return the singleton instance of this class.
     *
     * @return the instance.
     */
    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    /**
     * A private constructor created to ensure that this class is a singleton (i.e. that it
     * cannot be instantiated by external classes).
     */
    private UserService() {
        this.currentUser = null;
        this.displayUser = null;
        this.serverFacade = new ServerFacade();
    }

    /**
     * Returns the currently logged in user.
     *
     * @return the user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    private void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getDisplayUser() { return displayUser; }

    public void setDisplayUser(User displayUser) { this.displayUser = displayUser; }

    public AuthResponse signIn(AuthRequest request) {
        AuthResponse response = serverFacade.signIn(request);
        setCurrentUser(response.getUser());
        setDisplayUser(response.getUser());
        return response;
    }

    public AuthResponse signUp(AuthRequest request) {
        AuthResponse response = serverFacade.signUp(request);
        setCurrentUser(response.getUser());
        setDisplayUser(response.getUser());
        return response;
    }

    public User getUserByAlias(String alias) {
        return serverFacade.getUserByAlias(alias);
    }

    public boolean doesUserFollowUser(User user1, User user2) {
        return serverFacade.doesUserFollowUser(user1, user2);
    }
}
