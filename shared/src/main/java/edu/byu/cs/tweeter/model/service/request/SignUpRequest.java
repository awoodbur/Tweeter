package edu.byu.cs.tweeter.model.service.request;

public class SignUpRequest extends Request {

    private String firstName;
    private String lastName;
    private String alias;
    private String password;
    private String imageURL;

    private SignUpRequest() {
        super("<token>");
    }

    public SignUpRequest(String firstName, String lastName, String alias, String password, String imageURL, String token) {
        super(token);
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
