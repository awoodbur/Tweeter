package edu.byu.cs.tweeter.model.service.response;

public class FollowUserResponse extends Response {

    public FollowUserResponse(String message) {
        super(false, message);
    }

    public FollowUserResponse() {
        super(true);
    }
}
