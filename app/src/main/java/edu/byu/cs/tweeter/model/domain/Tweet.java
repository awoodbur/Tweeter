package edu.byu.cs.tweeter.model.domain;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class Tweet implements  Comparable<Tweet>, Serializable {

    private final User author;
    private final String content;
    private final String date;

    public Tweet(@NotNull User author, @NotNull String content, @NotNull String date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public User getAuthor() { return author; }

    public String getContent() { return content; }

    public String getDate() { return date; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return content.equals(tweet.content) && author.equals(tweet.author) && date.equals(tweet.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, content, date);
    }

    @NotNull
    @Override
    public String toString() {
        return "Tweet{" +
                "author='" + author.toString() +
                "', content='" + content +
                "', date=" + date + "'}";
    }

    @Override
    // TODO: Fix
    public int compareTo(Tweet tweet) {
        return this.getContent().compareTo(tweet.getContent())
                & this.getAuthor().compareTo(tweet.getAuthor());
    }
}
