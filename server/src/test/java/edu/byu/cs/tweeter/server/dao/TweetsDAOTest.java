package edu.byu.cs.tweeter.server.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import static org.junit.jupiter.api.Assertions.*;

class TweetsDAOTest {

    private TweetsDAO dao;

    @BeforeEach
    void setUp() {
        dao = new TweetsDAO();
    }

    @AfterEach
    void tearDown() {
        dao.reset();
    }

    @Test
    void shareTweet() {
        User user = new User("test");
        Tweet tweet = new Tweet(user, "12345");
        ShareTweetRequest request = new ShareTweetRequest(tweet);
        ShareTweetResponse response = dao.shareTweet(request);

        StoryRequest check = new StoryRequest(user, 10, null);
        StoryResponse checked = dao.getStory(check);
        assertEquals(1, checked.getTweets().size());
        assertEquals(user, checked.getTweets().get(0).getAuthor());
        assertEquals("12345", checked.getTweets().get(0).getContent());
    }

    @Test
    void getFeed() {
        User user = new User("kirk");
        FeedRequest request = new FeedRequest(user, 100, null);
        FeedResponse response = dao.getFeed(request);
        assertEquals(75, response.getTweets().size());
    }

    @Test
    void getStory() {
        User user = new User("kirk");
        StoryRequest request = new StoryRequest(user, 10, null);
        StoryResponse response = dao.getStory(request);
        assertEquals(10, response.getTweets().size());
        assertEquals(user, response.getTweets().get(0).getAuthor());
        assertEquals("kirk here with tweet #1", response.getTweets().get(0).getContent());
    }
}