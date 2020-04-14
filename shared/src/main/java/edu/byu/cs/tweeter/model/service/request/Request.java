package edu.byu.cs.tweeter.model.service.request;

public class Request {

    private final String token;

    public Request(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
