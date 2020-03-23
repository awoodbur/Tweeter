package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class CheckFollowRequest {

    private User follower;
    private User followee;

    private CheckFollowRequest() {}

    public CheckFollowRequest(User follower, User followee) {
        this.follower = follower;
        this.followee = followee;
    }

    public User getFollower() {
        return follower;
    }

    public User getFollowee() {
        return followee;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }
}
