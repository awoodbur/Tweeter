package edu.byu.cs.tweeter.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.request.FollowersRequest;
import edu.byu.cs.tweeter.net.response.FollowersResponse;
import edu.byu.cs.tweeter.presenter.FollowersPresenter;
import edu.byu.cs.tweeter.view.cache.ImageCache;
import edu.byu.cs.tweeter.view.util.ImageUtils;

/**
 * An {@link AsyncTask} for retrieving followers for a user.
 */
public class GetFollowersTask extends AsyncTask<FollowersRequest, Void, FollowersResponse> {

    private final FollowersPresenter presenter;
    private final GetFollowersObserver observer;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface GetFollowersObserver {
        void followersRetrieved(FollowersResponse followersResponse);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve followers.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public GetFollowersTask(FollowersPresenter presenter, GetFollowersObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    /**
     * The method that is invoked on the background thread to retrieve followers.
     *
     * @param followersRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected FollowersResponse doInBackground(FollowersRequest... followersRequests) {
        FollowersResponse response = presenter.getFollowers(followersRequests[0]);
        loadImages(response);
        return response;
    }

    /**
     * Loads the image associated with each follower included in the response.
     *
     * @param response the response from the follower request.
     */
    private void loadImages(FollowersResponse response) {
        for(User user : response.getFollowers()) {

            Drawable drawable;

            try {
                drawable = ImageUtils.drawableFromUrl(user.getImageUrl());
            } catch (IOException e) {
                Log.e(this.getClass().getName(), e.toString(), e);
                drawable = null;
            }

            ImageCache.getInstance().cacheImage(user, drawable);
        }
    }

    /**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param followersResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(FollowersResponse followersResponse) {

        if(observer != null) {
            observer.followersRetrieved(followersResponse);
        }
    }
}
