package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.CheckFollowServiceProxy;
import edu.byu.cs.tweeter.client.model.service.CurrentStateService;
import edu.byu.cs.tweeter.client.model.service.GetUserServiceProxy;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.CheckFollowService;
import edu.byu.cs.tweeter.model.service.GetUserService;
import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;

/**
 * A common base class for all presenters in the application.
 */
public abstract class Presenter {

    /**
     * Returns the currently logged in user.
     *
     * @return the user.
     */
    public User getCurrentUser() {
        return CurrentStateService.getInstance().getCurrentUser();
    }

    public User getDisplayUser() {
        return CurrentStateService.getInstance().getDisplayUser();
    }

    public String getAuthToken() { return CurrentStateService.getInstance().getAuthToken(); }

    public GetUserResponse getUser(GetUserRequest request) throws IOException {
        GetUserService service = new GetUserServiceProxy();
        return service.getUser(request);
    }

    public CheckFollowResponse checkFollow(CheckFollowRequest request) throws IOException {
        CheckFollowService service = new CheckFollowServiceProxy();
        return service.checkFollow(request);
    }

    public void setCurrentUser(User user) {
        CurrentStateService.getInstance().setCurrentUser(user);
    }

    public void setDisplayUser(User user) {
        CurrentStateService.getInstance().setDisplayUser(user);
    }

    public void setAuthToken(String token) {
        CurrentStateService.getInstance().setAuthToken(token);
    }
}
