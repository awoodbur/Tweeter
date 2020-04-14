package edu.byu.cs.tweeter.server.lambda.tweet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.server.dao.StoriesDAO;
import edu.byu.cs.tweeter.server.lambda.story.GetStoryHandler;

import static org.junit.jupiter.api.Assertions.*;

class ShareTweetHandlerTest {

    private ShareTweetHandler handler;
    private User user1;
    private Tweet tweet;
    private String content;

    @BeforeEach
    void setUp() {
        handler = new ShareTweetHandler();
        user1 = new User("test99", "test99", "test99", "test99");
        content = "This is my test tweet";
        tweet = new Tweet(user1, content);
    }

    @AfterEach
    void tearDown() {
        StoriesDAO storiesDAO = new StoriesDAO();
        storiesDAO.deleteTweet(user1, tweet.getDate());
    }

    @Test
    void shareTweet() {
        ShareTweetRequest request = new ShareTweetRequest(tweet, "token");
        ShareTweetResponse response = handler.handleRequest(request, null);
        assertTrue(response.isSuccess());

        StoriesDAO storiesDAO = new StoriesDAO();
        StoryResponse check = storiesDAO.getStory(new StoryRequest(user1, 10, null, "token"));
        assertEquals(1, check.getTweets().size());
        assertEquals(user1, check.getTweets().get(0).getAuthor());
        assertEquals(content, check.getTweets().get(0).getContent());
    }

    @Test
    void testTweet() {
        ShareTweetRequest request = new ShareTweetRequest(tweet, "token");
        System.out.println(request.getTweet().toString());
    }
}