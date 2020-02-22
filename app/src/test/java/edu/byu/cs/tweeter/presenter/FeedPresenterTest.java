package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.request.FeedRequest;
import edu.byu.cs.tweeter.net.response.FeedResponse;

import static org.junit.jupiter.api.Assertions.*;

class FeedPresenterTest {

    private User user1;
    private User user2;
    private Tweet tweet;
    private FeedRequest request;
    FeedPresenter presenter;
    private String content;

    @BeforeEach
    void setUp() {
        user1 = new User("Tester", "Testerson", "test1", "");
        user2 = new User("Tester", "Testerson", "test2", "");

        UserPresenter follower = new UserPresenter(null);
        follower.followUser(new Follow(user1, user2));

        content = "Test tweet";
        tweet = new Tweet(user2, content);

        request = new FeedRequest(user1, 1, null);
        presenter = new FeedPresenter(null);

        TweetPresenter tweeter = new TweetPresenter(null);
        tweeter.shareTweet(tweet);
    }

    @Test
    void getFeed() {
        FeedResponse response = presenter.getFeed(request);
        assertEquals(content, response.getTweets().get(0).getContent());
        assertEquals(user2, response.getTweets().get(0).getAuthor());
        assertEquals(tweet, response.getTweets().get(0));
    }
}