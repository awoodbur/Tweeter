package edu.byu.cs.tweeter.server.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

import static org.junit.jupiter.api.Assertions.*;

class UsersDAOTest {

    private UsersDAO dao;

    @BeforeEach
    void setUp() {
        dao = new UsersDAO();
    }

    @AfterEach
    void tearDown() {
        dao.reset();
    }

    @Test
    void signInNew() {
        SignInRequest request = new SignInRequest("none", "none");
        SignInResponse response = dao.signIn(request);
        assertEquals("User not found", response.getMessage());
    }

    @Test
    void signInBadPass() {
        SignInRequest request = new SignInRequest("kirk", "bad password");
        SignInResponse response = dao.signIn(request);
        assertEquals("Invalid password", response.getMessage());
    }

    @Test
    void signIn() {
        SignInRequest request = new SignInRequest("kirk", "password");
        SignInResponse response = dao.signIn(request);
        assertEquals(new User("kirk"), response.getUser());
    }

    @Test
    void signUpExists() {
        SignUpRequest request = new SignUpRequest("james", "kirk", "kirk", "password", "");
        SignUpResponse response = dao.signUp(request);
        assertEquals("User already exists", response.getMessage());
    }

    @Test
    void signUp() {
        SignUpRequest request = new SignUpRequest("test", "test", "test", "password", "");
        SignUpResponse response = dao.signUp(request);
        assertEquals(new User("test"), response.getUser());
    }

    @Test
    void signOut() {
        SignInRequest setup = new SignInRequest("kirk", "password");
        SignInResponse setup_resp = dao.signIn(setup);
        User user = setup_resp.getUser();

        SignOutRequest request = new SignOutRequest(user);
        SignOutResponse response = dao.signOut(request);
        assertTrue(response.isSuccess());
    }

    @Test
    void getUser() {
        GetUserRequest request = new GetUserRequest("kirk");
        GetUserResponse response = dao.getUser(request);
        assertEquals(new User("kirk"), response.getUser());
    }
}