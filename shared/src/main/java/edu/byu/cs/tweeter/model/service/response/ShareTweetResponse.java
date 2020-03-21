package edu.byu.cs.tweeter.model.service.response;

public class ShareTweetResponse extends Response {

    public ShareTweetResponse(String message) {
        super(false, message);
    }

    public ShareTweetResponse() {
        super(true);
    }
}
