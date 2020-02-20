package edu.byu.cs.tweeter.net.request;

public class AuthRequest {

    private final String firstName;
    private final String lastName;
    private final String handle;
    private final String password;
    private final String imageURL;

    public AuthRequest(String handle, String password) {
        this(null, null, handle, password, null);
    }

    public AuthRequest(String firstName, String lastName, String handle, String password, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.handle = handle;
        this.password = password;
        this.imageURL = imageURL;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getHandle() { return handle; }

    public String getPassword() { return password; }

    public String getImageURL() { return imageURL; }
}
