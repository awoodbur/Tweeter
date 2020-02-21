package edu.byu.cs.tweeter.model.domain;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Tweet implements  Comparable<Tweet>, Serializable {

    private final User author;
    private final String content;
    private final Timestamp date;
    private final long timestamp;

    public Tweet(@NotNull User author, @NotNull String content) {
        this.author = author;
        this.content = content;
        this.date = new Timestamp(System.currentTimeMillis());
        this.timestamp = date.getTime();
    }

    public User getAuthor() { return author; }

    public String getContent() { return content; }

    public String getDate() { return date.toString(); }

    public long getTimestamp() { return timestamp; }

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
    public int compareTo(Tweet tweet) {
        return this.getDate().compareTo(tweet.getDate());
    }
}
