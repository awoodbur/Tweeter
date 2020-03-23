package edu.byu.cs.tweeter.model.service.request;

public class GetUserRequest {

    private String alias;

    private GetUserRequest() {}

    public GetUserRequest(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
