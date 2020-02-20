package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.request.FeedRequest;
import edu.byu.cs.tweeter.net.response.FeedResponse;

/**
 * Contains the business logic for getting a users feed.
 */
public class FeedService {

    /**
     * The singleton instance.
     */
    private static FeedService instance;

    private final ServerFacade serverFacade;

    /**
     * Return the singleton instance of this class.
     *
     * @return the instance.
     */
    public static FeedService getInstance() {
        if(instance == null) {
            instance = new FeedService();
        }

        return instance;
    }

    /**
     * A private constructor created to ensure that this class is a singleton (i.e. that it
     * cannot be instantiated by external classes).
     */
    private FeedService() {
        serverFacade = new ServerFacade();
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. Uses the {@link ServerFacade} to
     * get the followees from the server.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FeedResponse getFeed(FeedRequest request) {
        return serverFacade.getFeed(request);
    }}
