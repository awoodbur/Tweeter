package edu.byu.cs.tweeter.model.service.response;

public class UnfollowUserResponse extends Response {

    public UnfollowUserResponse(String message) {
        super(false, message);
    }

    public UnfollowUserResponse() {
        super(true);
    }
}
