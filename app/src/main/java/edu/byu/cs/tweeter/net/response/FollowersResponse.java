package edu.byu.cs.tweeter.net.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;

/**
 * A paged response for a {@link edu.byu.cs.tweeter.net.request.FollowersRequest}.
 */
public class FollowersResponse extends PagedResponse {

    private List<User> followers;

    /**
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success and more pages indicators to false.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public FollowersResponse(String message) {
        super(false, message, false);
    }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param followers the followees to be included in the result.
     * @param hasMorePages an indicator or whether more data is available for the request.
     */
    public FollowersResponse(List<User> followers, boolean hasMorePages) {
        super(true, hasMorePages);
        this.followers = followers;
    }

    /**
     * Returns the followers for the corresponding request.
     *
     * @return the followers.
     */
    public List<User> getFollowers() {
        return followers;
    }
}
