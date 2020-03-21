package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import static org.junit.jupiter.api.Assertions.*;

class StoryPresenterTest {

    private User user;
    private Tweet tweet;
    private StoryRequest request;
    StoryPresenter presenter;
    private String content;

    @BeforeEach
    void setUp() {
        content = "Test tweet";
        user = new User("Tester", "Testerson", "test", "");
        tweet = new Tweet(user, content);

        request = new StoryRequest(user, 10, null);

        presenter = new StoryPresenter(null);

        TweetPresenter tweeter = new TweetPresenter(null);
        tweeter.shareTweet(tweet);
    }

    @Test
    void getStory() {
        StoryResponse response = presenter.getStory(request);
        assertEquals(content, response.getTweets().get(0).getContent());
        assertEquals(user, response.getTweets().get(0).getAuthor());
        assertEquals(tweet, response.getTweets().get(0));
    }
}