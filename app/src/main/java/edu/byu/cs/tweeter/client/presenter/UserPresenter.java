package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.SignOutServiceProxy;
import edu.byu.cs.tweeter.client.model.service.UserServiceProxy;
import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.service.SignOutService;
import edu.byu.cs.tweeter.model.service.UserService;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;

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

    public Response followUser(Follow follow) {
        UserService service = new UserServiceProxy();
        return service.followUser(follow);
    }

    public Response unfollowUser(Follow follow) {
        UserService service = new UserServiceProxy();
        return service.unfollowUser(follow);
    }

    public SignOutResponse signOut(SignOutRequest request) throws IOException {
        SignOutService service = new SignOutServiceProxy();
        return service.signOut(request);
    }
}
