package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import static org.junit.jupiter.api.Assertions.*;

class TweetPresenterTest {

    private User user;
    private Tweet tweet;
    private String content;
    TweetPresenter presenter;
    StoryPresenter story;

    @BeforeEach
    void setUp() {
        content = "Test tweet";
        user = new User("Tester", "Testerson", "test", "");
        tweet = new Tweet(user, content);

        presenter = new TweetPresenter(null);
        story = new StoryPresenter(null);
    }

    @Test
    void shareTweet() throws IOException  {
        ShareTweetRequest tweet_request = new ShareTweetRequest(tweet, "token");
        Response response = presenter.shareTweet(tweet_request);
        assertTrue(response.isSuccess());

        StoryResponse storyResponse = story.getStory(new StoryRequest(user, 1, null, "token"));
        assertEquals(tweet, storyResponse.getTweets().get(0));
    }
}