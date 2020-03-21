package edu.byu.cs.tweeter.model.service.request;

public class GetUserRequest {

    private String alias;

    public GetUserRequest(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
