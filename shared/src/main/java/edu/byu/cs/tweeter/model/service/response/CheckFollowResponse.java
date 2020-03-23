package edu.byu.cs.tweeter.model.service.response;

public class CheckFollowResponse extends Response {

    private boolean following;

    public CheckFollowResponse(String message) {
        super(false, message);
    }

    public CheckFollowResponse(boolean follows) {
        super(true);
        this.following = follows;
    }

    public boolean isFollowing() {
        return following;
    }
}
