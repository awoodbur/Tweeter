package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;

class MainPresenterTest {

    private User user1;
    private User user2;
    MainPresenter presenter;

    @BeforeEach
    void setUp() throws IOException {
        user1 = new User("Tester", "Testerson", "test1", "");
        user2 = new User("Tester", "Testerson", "test2", "");

        UserPresenter follower = new UserPresenter(null);
        follower.followUser(new FollowUserRequest(user1, user2));
        follower.followUser(new FollowUserRequest(user2, user1));
    }
}