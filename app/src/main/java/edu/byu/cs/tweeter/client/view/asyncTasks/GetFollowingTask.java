package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.client.presenter.FollowingPresenter;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;

/**
 * An {@link AsyncTask} for retrieving followees for a user.
 */
public class GetFollowingTask extends AsyncTask<FollowingRequest, Void, FollowingResponse> {

    private final FollowingPresenter presenter;
    private final GetFolloweesObserver observer;

    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface GetFolloweesObserver {
        void followeesRetrieved(FollowingResponse followingResponse);
        void handleException(Exception e);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve followees.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public GetFollowingTask(FollowingPresenter presenter, GetFolloweesObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    /**
     * The method that is invoked on the background thread to retrieve followees.
     *
     * @param followingRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected FollowingResponse doInBackground(FollowingRequest... followingRequests) {
        FollowingResponse response = null;
        try {
            response = presenter.getFollowing(followingRequests[0]);
            loadImages(response);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    /**
     * Loads the image associated with each followee included in the response.
     *
     * @param response the response from the followee request.
     */
    private void loadImages(FollowingResponse response) {
        for(User user : response.getFollowees()) {

            Drawable drawable;

            try {
                if (user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                    drawable = ImageUtils.drawableFromUrl(user.getImageUrl());
                } else {
                    drawable = null;
                }
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
     * @param followingResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(FollowingResponse followingResponse) {

        if(observer != null) {
            if (exception == null) {
                observer.followeesRetrieved(followingResponse);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
