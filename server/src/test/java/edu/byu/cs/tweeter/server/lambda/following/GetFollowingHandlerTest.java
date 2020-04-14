package edu.byu.cs.tweeter.server.lambda.following;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

import static org.junit.jupiter.api.Assertions.*;

class GetFollowingHandlerTest {

    private GetFollowingHandler handler;
    private User user1;
    private User user2;
    private List<User> users;

    @BeforeEach
    void setUp() {
        handler = new GetFollowingHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        user2 = new User("test100", "test100", "test100", "test100");
        FollowsDAO followsDAO = new FollowsDAO();
        users = new ArrayList<>();
        for (int i = 0; i < 25; ++i) {
            String info = "test" + i;
            User user = new User(info, info, info, info);
            users.add(user);
            followsDAO.followUser(new FollowUserRequest(user1, user, "token"));
        }
    }

    @AfterEach
    void tearDown() {
        FollowsDAO followsDAO = new FollowsDAO();
        for (User user : users) {
            followsDAO.unfollowUser(new UnfollowUserRequest(user1, user, "token"));
        }
    }

    @Test
    void getFollowing() {
        FollowingRequest request = new FollowingRequest(user1, 25, null, "token");
        FollowingResponse response = handler.handleRequest(request, null);
        assertEquals(25, response.getFollowees().size());
    }

    @Test
    void getFollowingPaged() {
        FollowingRequest first_req = new FollowingRequest(user1, 10, null, "token");
        FollowingResponse first_resp = handler.handleRequest(first_req, null);
        User lastFollower = first_resp.getFollowees().get(9);

        FollowingRequest request = new FollowingRequest(user1, 10, lastFollower, "token");
        FollowingResponse response = handler.handleRequest(request, null);
        assertNotEquals(lastFollower, response.getFollowees().get(0));
    }
}