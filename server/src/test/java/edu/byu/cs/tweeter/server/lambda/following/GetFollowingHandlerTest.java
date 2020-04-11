package edu.byu.cs.tweeter.server.lambda.following;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

import static org.junit.jupiter.api.Assertions.*;

class GetFollowingHandlerTest {

    private GetFollowingHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GetFollowingHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFollowing() {
        User follower = new User("kirk");
        FollowingRequest request = new FollowingRequest(follower, 10, null);
        FollowingResponse response = handler.handleRequest(request, null);

        List<User> followees = new ArrayList<>();
        followees.add(new User("spock"));
        followees.add(new User("bones"));
        followees.add(new User("scotty"));
        assertEquals(followees, response.getFollowees());
    }

    @Test
    void getFollowingPaged() {
        User follower = new User("bones");
        FollowingRequest setup = new FollowingRequest(follower, 10, null);
        FollowingResponse setup_resp = handler.handleRequest(setup, null);
        User lastFollower = setup_resp.getFollowees().get(9);

        FollowingRequest request = new FollowingRequest(follower, 10, lastFollower);
        FollowingResponse response = handler.handleRequest(request, null);
        assertNotEquals(lastFollower, response.getFollowees().get(0));
    }
}