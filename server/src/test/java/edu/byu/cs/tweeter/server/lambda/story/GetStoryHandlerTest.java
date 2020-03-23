package edu.byu.cs.tweeter.server.lambda.story;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        StoryRequest request = new StoryRequest(user, 10, null);
        StoryResponse response = handler.handleRequest(request, null);
        assertEquals(10, response.getTweets().size());
        assertEquals(user, response.getTweets().get(0).getAuthor());
        assertEquals("kirk here with tweet #1", response.getTweets().get(0).getContent());
    }
}