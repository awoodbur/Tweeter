package edu.byu.cs.tweeter.server.lambda.story;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.server.dao.StoriesDAO;

import static org.junit.jupiter.api.Assertions.*;

class GetStoryHandlerTest {

    private GetStoryHandler handler;
    private User user1;
    private User user2;
    private List<Long> times;

    @BeforeEach
    void setUp() {
        handler = new GetStoryHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        user2 = new User("test100", "test100", "test100", "test100");
        StoriesDAO storiesDAO = new StoriesDAO();
        times = new ArrayList<>();
        for (int i = 0; i < 25; ++i) {
            Tweet tweet = new Tweet(user1, "My random content " + i);
            times.add(tweet.getDate());
            storiesDAO.shareTweet(new ShareTweetRequest(tweet, "token"));
        }
    }

    @AfterEach
    void tearDown() {
        StoriesDAO storiesDAO = new StoriesDAO();
        for (Long time : times) {
            storiesDAO.deleteTweet(user1, time);
        }
    }

    @Test
    void getStory() {
        StoryRequest request = new StoryRequest(user1, 25, null, "token");
        StoryResponse response = handler.handleRequest(request, null);
        assertEquals(25, response.getTweets().size());
        assertEquals(user1, response.getTweets().get(0).getAuthor());
    }

    @Test
    void getStoryPage() {
        StoryRequest first_req = new StoryRequest(user1, 10, null, "token");
        StoryResponse first_resp = handler.handleRequest(first_req, null);
        Tweet lastTweet = first_resp.getTweets().get(9);

        StoryRequest request = new StoryRequest(user1, 10, lastTweet, "token");
        StoryResponse response = handler.handleRequest(request, null);
        assertNotEquals(lastTweet, response.getTweets().get(0));
    }
}