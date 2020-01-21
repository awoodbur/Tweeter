package edu.byu.cs.tweeter.view.android.domain;

import android.graphics.drawable.Drawable;

public class User extends edu.byu.cs.tweeter.model.domain.User {

    private Drawable image;

    public User(String firstName, String lastName, String imageUrl) {
        this(firstName, lastName, imageUrl, null);
    }

    public User(String firstName, String lastName, String imageUrl, Drawable image) {
        super(firstName, lastName, imageUrl);
        this.image = image;
    }

    public edu.byu.cs.tweeter.model.domain.User getModelUser() {
        return new edu.byu.cs.tweeter.model.domain.User(getFirstName(), getLastName(), getImageUrl());
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}