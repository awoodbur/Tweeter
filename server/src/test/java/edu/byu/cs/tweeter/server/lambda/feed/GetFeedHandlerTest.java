package edu.byu.cs.tweeter.server.lambda.feed;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

import static org.junit.jupiter.api.Assertions.*;

class GetFeedHandlerTest {

    private GetFeedHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GetFeedHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFeed() {
        User user = new User("kirk");
        FeedRequest request = new FeedRequest(user, 100, null);
        FeedResponse response = handler.handleRequest(request, null);
        assertEquals(75, response.getTweets().size());
    }

    @Test
    void getFeedPaged() {
        User user = new User("kirk");
        FeedRequest setup = new FeedRequest(user, 10, null);
        FeedResponse setup_resp = handler.handleRequest(setup, null);
        Tweet lastTweet = setup_resp.getTweets().get(9);

        FeedRequest request = new FeedRequest(user, 10, lastTweet);
        FeedResponse response = handler.handleRequest(request, null);
        assertNotEquals(lastTweet, response.getTweets().get(0));
    }
}