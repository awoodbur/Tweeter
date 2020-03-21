package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.model.service.response.Response;

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
        return UserService.getInstance().followUser(follow);
    }

    public Response unfollowUser(Follow follow) {
        return UserService.getInstance().unfollowUser(follow);
    }
}
