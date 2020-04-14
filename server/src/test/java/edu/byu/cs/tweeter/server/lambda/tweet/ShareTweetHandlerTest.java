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
import edu.byu.cs.tweeter.server.lambda.story.GetStoryHandler;

import static org.junit.jupiter.api.Assertions.*;

class ShareTweetHandlerTest {

    private ShareTweetHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ShareTweetHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shareTweet() {
        User user = new User("test");
        Tweet tweet = new Tweet(user, "12345");
        ShareTweetRequest request = new ShareTweetRequest(tweet, "token");
        ShareTweetResponse response = handler.handleRequest(request, null);

        GetStoryHandler check_handler = new GetStoryHandler();
        StoryRequest check = new StoryRequest(user, 10, null, "token");
        StoryResponse checked = check_handler.handleRequest(check, null);
        assertEquals(1, checked.getTweets().size());
        assertEquals(user, checked.getTweets().get(0).getAuthor());
        assertEquals("12345", checked.getTweets().get(0).getContent());
    }
}