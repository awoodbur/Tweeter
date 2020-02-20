package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.request.AuthRequest;
import edu.byu.cs.tweeter.net.response.AuthResponse;

/**
 * Contains the business logic for login and sign up.
 */
public class LoginService {

    /**
     * The singleton instance.
     */
    private static LoginService instance;

    /**
     * Communication with server.
     */
    private final ServerFacade serverFacade;

    /**
     * The logged in user.
     */
    private User currentUser;

    /**
     * Return the singleton instance of this class.
     *
     * @return the instance.
     */
    public static LoginService getInstance() {
        if(instance == null) {
            instance = new LoginService();
        }

        return instance;
    }

    /**
     * A private constructor created to ensure that this class is a singleton (i.e. that it
     * cannot be instantiated by external classes).
     */
    private LoginService() {
        this.currentUser = null;
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

    public AuthResponse signIn(AuthRequest request) {
        AuthResponse response = serverFacade.signIn(request);
        setCurrentUser(response.getUser());
        return response;
    }

    public AuthResponse signUp(AuthRequest request) {
        AuthResponse response = serverFacade.signUp(request);
        setCurrentUser(response.getUser());
        return response;    }
}
