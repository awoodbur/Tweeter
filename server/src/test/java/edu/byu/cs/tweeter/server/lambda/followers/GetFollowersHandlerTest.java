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
        User follower = new User("spock");
        FollowersRequest request = new FollowersRequest(follower, 10, null);
        FollowersResponse response = handler.handleRequest(request, null);
        List<User> followers = new ArrayList<>();
        followers.add(new User("kirk"));
        followers.add(new User("bones"));
        followers.add(new User("uhura"));
        assertEquals(followers, response.getFollowers());
    }

    @Test
    void getFollowersPaged() {
        User follower = new User("kirk");
        FollowersRequest setup = new FollowersRequest(follower, 10, null);
        FollowersResponse setup_resp = handler.handleRequest(setup, null);
        User lastFollower = setup_resp.getFollowers().get(9);

        FollowersRequest request = new FollowersRequest(follower, 10, lastFollower);
        FollowersResponse response = handler.handleRequest(request, null);
        assertNotEquals(lastFollower, response.getFollowers().get(0));
    }
}