package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.client.presenter.UserPresenter;

public class FollowUserTask extends AsyncTask<FollowUserRequest, Void, FollowUserResponse> {

    private final UserPresenter presenter;
    private final FollowUserObserver observer;

    private Exception exception;

    public interface FollowUserObserver {
        void followUserComplete(FollowUserResponse response);
        void handleException(Exception e);
    }

    public FollowUserTask(UserPresenter presenter, FollowUserObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected FollowUserResponse doInBackground(FollowUserRequest... followUserRequests) {
        FollowUserResponse response = null;
        try {
            response = presenter.followUser(followUserRequests[0]);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(FollowUserResponse response) {
        if (observer != null) {
            if (exception == null) {
                observer.followUserComplete(response);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
