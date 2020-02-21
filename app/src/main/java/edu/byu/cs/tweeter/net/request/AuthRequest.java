package edu.byu.cs.tweeter.net.request;

import java.security.MessageDigest;

public class AuthRequest {

    private final String firstName;
    private final String lastName;
    private final String alias;
    private final String password;
    private final String imageURL;

    private static final String HASH_ALGORITHM = "SHA-256";

    public AuthRequest(String alias, String password) {
        this(null, null, alias, password, null);
    }

    public AuthRequest(String firstName, String lastName, String alias, String password, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.password = encrpytPassword(password);
        this.imageURL = imageURL;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getAlias() { return alias; }

    public String getPassword() { return password; }

    public String getImageURL() { return imageURL; }

    private String encrpytPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            messageDigest.update(password.getBytes());
            return new String(messageDigest.digest());
        }
        catch (java.security.NoSuchAlgorithmException e) {
            // Never
        }
        return null;
    }
}
