package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;

import static org.junit.jupiter.api.Assertions.*;

class LoginPresenterTest {

    private User user;
    LoginPresenter presenter;

    @BeforeEach
    void setUp() {
        user = new User("Tester", "Testerson", "test", "");
        presenter = new LoginPresenter(null);
    }

    @Test
    void testCurrentUser() {
        presenter.setCurrentUser(user);
        User currUser = presenter.getCurrentUser();
        assertEquals(user, currUser);
    }

    @Test
    void testDisplayUser() {
        presenter.setDisplayUser(user);
        User dispUser = presenter.getDisplayUser();
        assertEquals(user, dispUser);
    }
}