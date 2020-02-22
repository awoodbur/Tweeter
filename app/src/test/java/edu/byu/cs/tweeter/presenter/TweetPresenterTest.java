package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.request.StoryRequest;
import edu.byu.cs.tweeter.net.response.Response;
import edu.byu.cs.tweeter.net.response.StoryResponse;

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
    void shareTweet() {
        Response response = presenter.shareTweet(tweet);
        assertTrue(response.isSuccess());

        StoryResponse storyResponse = story.getStory(new StoryRequest(user, 1, null));
        assertEquals(tweet, storyResponse.getTweets().get(0));
    }
}