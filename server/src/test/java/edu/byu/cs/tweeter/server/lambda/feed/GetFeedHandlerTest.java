package edu.byu.cs.tweeter.server.lambda.feed;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.FeedsDAO;

import static org.junit.jupiter.api.Assertions.*;

class GetFeedHandlerTest {

    private GetFeedHandler handler;
    private User user1;
    private User user2;
    private List<Long> times;

    @BeforeEach
    void setUp() {
        handler = new GetFeedHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        user2 = new User("test100", "test100", "test100", "test100");
        FeedsDAO feedsDAO = new FeedsDAO();
        times = new ArrayList<>();
        for (int i = 0; i < 25; ++i) {
            Tweet tweet = new Tweet(user1, "My random content " + i);
            times.add(tweet.getDate());
            feedsDAO.shareTweet(new ShareTweetRequest(tweet, "token"), user2);
        }
    }

    @AfterEach
    void tearDown() {
        FeedsDAO feedsDAO = new FeedsDAO();
        for (Long time : times) {
            feedsDAO.deleteTweet(user2, time);
        }
    }

    @Test
    void getFeed() {
        FeedRequest request = new FeedRequest(user2, 25, null, "token");
        FeedResponse response = handler.handleRequest(request, null);
        assertEquals(25, response.getTweets().size());
    }

    @Test
    void getFeedPaged() {
        FeedRequest first_req = new FeedRequest(user2, 10, null, "token");
        FeedResponse first_resp = handler.handleRequest(first_req, null);
        Tweet lastTweet = first_resp.getTweets().get(9);

        FeedRequest request = new FeedRequest(user2, 10, lastTweet, "token");
        FeedResponse response = handler.handleRequest(request, null);
        assertNotEquals(lastTweet, response.getTweets().get(0));
    }
}