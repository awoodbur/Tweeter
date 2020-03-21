package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.FollowUserServiceProxy;
import edu.byu.cs.tweeter.client.model.service.SignOutServiceProxy;
import edu.byu.cs.tweeter.client.model.service.UnfollowUserServiceProxy;
import edu.byu.cs.tweeter.model.service.FollowUserService;
import edu.byu.cs.tweeter.model.service.SignOutService;
import edu.byu.cs.tweeter.model.service.UnfollowUserService;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

/**
 * The presenter for the user activity.
 */
public class UserPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter sends notifications to it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public UserPresenter(View view) {
        this.view = view;
    }

    public FollowUserResponse followUser(FollowUserRequest request) throws IOException {
        FollowUserService service = new FollowUserServiceProxy();
        return service.followUser(request);
    }

    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request) throws IOException {
        UnfollowUserService service = new UnfollowUserServiceProxy();
        return service.unfollowUser(request);
    }

    public SignOutResponse signOut(SignOutRequest request) throws IOException {
        SignOutService service = new SignOutServiceProxy();
        return service.signOut(request);
    }
}
