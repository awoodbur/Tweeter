package edu.byu.cs.tweeter.server.lambda.followers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

import static org.junit.jupiter.api.Assertions.*;

class GetFollowersHandlerTest {

    private GetFollowersHandler handler;
    private User user1;
    private User user2;
    private List<User> users;

    @BeforeEach
    void setUp() {
        handler = new GetFollowersHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        user2 = new User("test100", "test100", "test100", "test100");
        FollowsDAO followsDAO = new FollowsDAO();
        users = new ArrayList<>();
        for (int i = 0; i < 25; ++i) {
            String info = "test" + i;
            User user = new User(info, info, info, info);
            users.add(user);
            followsDAO.followUser(new FollowUserRequest(user, user2, "token"));
        }
    }

    @AfterEach
    void tearDown() {
        FollowsDAO followsDAO = new FollowsDAO();
        for (User user : users) {
            followsDAO.unfollowUser(new UnfollowUserRequest(user, user2, "token"));
        }
    }

    @Test
    void getFollowers() {
        FollowersRequest request = new FollowersRequest(user2, 25, null, "token");
        FollowersResponse response = handler.handleRequest(request, null);
        assertEquals(25, response.getFollowers().size());
    }

    @Test
    void getFollowersPaged() {
        FollowersRequest first_req = new FollowersRequest(user2, 10, null, "token");
        FollowersResponse first_resp = handler.handleRequest(first_req, null);
        User lastFollower = first_resp.getFollowers().get(9);

        FollowersRequest request = new FollowersRequest(user2, 10, lastFollower, "token");
        FollowersResponse response = handler.handleRequest(request, null);
        assertNotEquals(lastFollower, response.getFollowers().get(0));
    }
}