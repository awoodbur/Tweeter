package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;

import static org.junit.jupiter.api.Assertions.*;

class SignInPresenterTest {

    SignInRequest signin;
    SignUpRequest signup;
    SignInPresenter presenter;
    private String alias;

    @BeforeEach
    void setUp() throws IOException {
        alias = "test";
        signin = new SignInRequest(alias, "password");
        signup = new SignUpRequest("Tester", "Testerson", alias, "password", "");
        SignUpPresenter create = new SignUpPresenter(null);
        create.signUp(signup);

        presenter = new SignInPresenter(null);
    }

    @Test
    void signIn() throws IOException {
        SignInResponse response = presenter.signIn(signin);
        assertTrue(response.isSuccess());
        assertEquals(alias, presenter.getCurrentUser().getAlias());
    }
}