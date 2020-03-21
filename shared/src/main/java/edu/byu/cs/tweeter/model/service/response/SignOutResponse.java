package edu.byu.cs.tweeter.model.service.response;

public class SignOutResponse extends Response {

    public SignOutResponse(String message) {
        super(false, message);
    }

    public SignOutResponse() {
        super(true);
    }
}
