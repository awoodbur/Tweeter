package edu.byu.cs.tweeter.model.service.request;

public class SignUpRequest {

    private final String firstName;
    private final String lastName;
    private final String alias;
    private final String password;
    private final String imageURL;

    public SignUpRequest(String firstName, String lastName, String alias, String password, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.password = password;
        this.imageURL = imageURL;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    public String getImageURL() {
        return imageURL;
    }
}
