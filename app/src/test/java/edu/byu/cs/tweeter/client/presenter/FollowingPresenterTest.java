package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

import static org.junit.jupiter.api.Assertions.*;

class FollowingPresenterTest {

    private User user1;
    private User user2;
    private FollowingRequest request;
    FollowingPresenter presenter;

    @BeforeEach
    void setUp() {
        user1 = new User("Tester", "Testerson", "test1", "");
        user2 = new User("Tester", "Testerson", "test2", "");

        UserPresenter follower = new UserPresenter(null);
        follower.followUser(new Follow(user1, user2));

        request = new FollowingRequest(user1, 1, null);
        presenter = new FollowingPresenter(null);
    }

    @Test
    void getFollowing() {
        FollowingResponse response = presenter.getFollowing(request);
        assertEquals(user2, response.getFollowees().get(0));
    }
}