package edu.byu.cs.tweeter.server.lambda.story;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import static org.junit.jupiter.api.Assertions.*;

class GetStoryHandlerTest {

    private GetStoryHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GetStoryHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStory() {
        User user = new User("kirk");
        StoryRequest request = new StoryRequest(user, 10, null, "token");
        StoryResponse response = handler.handleRequest(request, null);
        assertEquals(10, response.getTweets().size());
        assertEquals(user, response.getTweets().get(0).getAuthor());
        assertEquals("kirk here with tweet #1", response.getTweets().get(0).getContent());
    }

    @Test
    void getStoryPage() {
        User user = new User("kirk");
        StoryRequest setup = new StoryRequest(user, 10, null, "token");
        StoryResponse setup_resp = handler.handleRequest(setup, null);
        Tweet lastTweet = setup_resp.getTweets().get(9);

        StoryRequest request = new StoryRequest(user, 10, lastTweet, "token");
        StoryResponse response = handler.handleRequest(request, null);
        assertNotEquals(lastTweet, response.getTweets().get(0));
    }
}