package edu.byu.cs.tweeter.net.request;

public class AuthRequest {

    private final String firstName;
    private final String lastName;
    private final String alias;
    private final String password;
    private final String imageURL;

    public AuthRequest(String alias, String password) {
        this(null, null, alias, password, null);
    }

    public AuthRequest(String firstName, String lastName, String alias, String password, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.password = password;
        this.imageURL = imageURL;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getAlias() { return alias; }

    public String getPassword() { return password; }

    public String getImageURL() { return imageURL; }
}
