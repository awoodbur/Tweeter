package edu.byu.cs.tweeter.model.service.response;

public class CheckFollowResponse extends Response {

    public CheckFollowResponse(String message) {
        super(false, message);
    }

    public CheckFollowResponse() {
        super(true);
    }
}
