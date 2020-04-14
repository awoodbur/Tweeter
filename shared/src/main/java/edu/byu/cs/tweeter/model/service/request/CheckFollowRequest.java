package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class CheckFollowRequest {

    private User follower;
    private User followee;
    private String token;

    private CheckFollowRequest() {}

    public CheckFollowRequest(User follower, User followee, String token) {
        this.follower = follower;
        this.followee = followee;
        this.token = token;
    }

    public User getFollower() {
        return follower;
    }

    public User getFollowee() {
        return followee;
    }

    public String getToken() {
        return token;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
