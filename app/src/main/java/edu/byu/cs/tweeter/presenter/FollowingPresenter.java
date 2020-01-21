package edu.byu.cs.tweeter.presenter;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.Server;
import edu.byu.cs.tweeter.model.net.ServerProxy;
import edu.byu.cs.tweeter.model.net.request.FollowingRequest;
import edu.byu.cs.tweeter.model.net.response.FollowingResponse;

public class FollowingPresenter {

    private static FollowingPresenter instance;

    private final List<View> registeredViews = new ArrayList<>();

    /**
     * The interface by which this presenter communicates with it's views. Views that use this
     * presenter must implement this interface and must register themselves with the presenter
     * by calling {@link #registerView(View)}
     */
    public interface View {
        // If needed, Specify methods here that will be called on the views in response to model updates
    }

    public static FollowingPresenter getInstance() {
        if(instance == null) {
            instance = new FollowingPresenter();
        }

        return instance;
    }

    private FollowingPresenter() {}

    public void registerView(View view) {
        registeredViews.add(view);
    }

    public FollowingResponse<User> getFollowing(FollowingRequest request) {
        Server server = new ServerProxy();
        return server.getFollowees(request);
    }
}
