package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.User;

public class CurrentStateService {

    /**
     * The singleton instance.
     */
    private static CurrentStateService instance;

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

    private String authToken;

    /**
     * Return the singleton instance of this class.
     *
     * @return the instance.
     */
    public static CurrentStateService getInstance() {
        if (instance == null) {
            instance = new CurrentStateService();
        }
        return instance;
    }

    /**
     * A private constructor created to ensure that this class is a singleton (i.e. that it
     * cannot be instantiated by external classes).
     */
    private CurrentStateService() {
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

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getDisplayUser() {
        return displayUser;
    }

    public void setDisplayUser(User displayUser) {
        this.displayUser = displayUser;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
