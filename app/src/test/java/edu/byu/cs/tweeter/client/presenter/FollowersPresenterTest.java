package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

import static org.junit.jupiter.api.Assertions.*;

class FollowersPresenterTest {

    private User user1;
    private User user2;
    private FollowersRequest request;
    FollowersPresenter presenter;
    private String content;

    @BeforeEach
    void setUp() {
        user1 = new User("Tester", "Testerson", "test1", "");
        user2 = new User("Tester", "Testerson", "test2", "");

        UserPresenter follower = new UserPresenter(null);
        follower.followUser(new Follow(user1, user2));

        request = new FollowersRequest(user2, 1, null);
        presenter = new FollowersPresenter(null);
    }

    @Test
    void getFollowers() {
        FollowersResponse response = presenter.getFollowers(request);
        assertEquals(user1, response.getFollowers().get(0));
    }
}