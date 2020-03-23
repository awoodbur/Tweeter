package edu.byu.cs.tweeter.server.lambda.followers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

import static org.junit.jupiter.api.Assertions.*;

class GetFollowersHandlerTest {

    private GetFollowersHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GetFollowersHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFollowers() {
        User follower = new User("kirk");
        FollowersRequest request = new FollowersRequest(follower, 10, null);
        FollowersResponse response = handler.handleRequest(request, null);
        List<User> followers = new ArrayList<>();
        followers.add(new User("spock"));
        followers.add(new User("bones"));
        followers.add(new User("sulu"));
        followers.add(new User("uhura"));
        followers.add(new User("scotty"));
        followers.add(new User("chekov"));
        assertEquals(followers, response.getFollowers());
    }
}