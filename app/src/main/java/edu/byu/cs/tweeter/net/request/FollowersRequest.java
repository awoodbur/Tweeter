package edu.byu.cs.tweeter.net.request;

import edu.byu.cs.tweeter.model.domain.User;

public class FollowersRequest {

    private final User followee;
    private final int limit;
    private final User lastFollower;

    /**
     * Creates an instance.
     *
     * @param followee the {@link User} whose followees are to be returned.
     * @param limit the maximum number of followees to return.
     * @param lastFollower the last followee that was returned in the previous request (null if
     *                     there was no previous request or if no followees were returned in the
     *                     previous request).
     */
    public FollowersRequest(User followee, int limit, User lastFollower) {
        this.followee = followee;
        this.limit = limit;
        this.lastFollower = lastFollower;
    }

    /**
     * Returns the follower whose followees are to be returned by this request.
     *
     * @return the follower.
     */
    public User getFollowee() {
        return followee;
    }

    /**
     * Returns the number representing the maximum number of followees to be returned by this request.
     *
     * @return the limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Returns the last followee that was returned in the previous request or null if there was no
     * previous request or if no followees were returned in the previous request.
     *
     * @return the last followee.
     */
    public User getLastFollower() {
        return lastFollower;
    }
}
