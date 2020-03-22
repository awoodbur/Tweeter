package edu.byu.cs.tweeter.model.service.response;

public class CheckFollowResponse extends Response {

    private boolean follows;

    public CheckFollowResponse(String message) {
        super(false, message);
    }

    public CheckFollowResponse(boolean follows) {
        super(true);
        this.follows = follows;
    }

    public boolean follows() {
        return follows;
    }
}
