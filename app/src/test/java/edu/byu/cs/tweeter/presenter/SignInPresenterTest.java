package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.request.AuthRequest;
import edu.byu.cs.tweeter.net.response.AuthResponse;

import static org.junit.jupiter.api.Assertions.*;

class SignInPresenterTest {

    AuthRequest signin;
    AuthRequest signup;
    SignInPresenter presenter;
    private String alias;

    @BeforeEach
    void setUp() {
        alias = "test";
        signin = new AuthRequest(alias, "password");
        signup = new AuthRequest("Tester", "Testerson", alias, "password", "");
        SignUpPresenter create = new SignUpPresenter(null);
        create.signUp(signup);

        presenter = new SignInPresenter(null);
    }

    @Test
    void signIn() {
        AuthResponse response = presenter.signIn(signin);
        assertTrue(response.isSuccess());
        assertEquals(alias, presenter.getCurrentUser().getAlias());
    }
}