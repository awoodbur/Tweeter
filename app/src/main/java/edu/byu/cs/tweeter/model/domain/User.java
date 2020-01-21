package edu.byu.cs.tweeter.model.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class User implements Comparable<User> {

    private final String firstName;
    private final String lastName;
    private final String handle;
    private final String imageUrl;

    public User(@NotNull String firstName, @NotNull String lastName, String imageURL) {
        this(firstName, lastName, String.format("@%s%s", firstName, lastName), imageURL);
    }

    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String handle, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.handle = handle;
        this.imageUrl = imageURL;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getHandle() {
        return handle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return handle.equals(user.handle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(handle);
    }

    @NotNull
    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", handle='" + handle + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public int compareTo(User user) {
        return this.getHandle().compareTo(user.getHandle());
    }
}
