package edu.byu.cs.tweeter.model.service.request;

public class GetUserRequest extends Request {

    private String alias;

    private GetUserRequest() {
        super("<token>");
    }

    public GetUserRequest(String alias, String token) {
        super(token);
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
