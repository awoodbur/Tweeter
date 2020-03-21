package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.client.presenter.UserPresenter;

public class FollowUserTask extends AsyncTask<Follow, Void, Response> {

    private final UserPresenter presenter;
    private final FollowUserObserver observer;

    public interface FollowUserObserver {
        void followUserComplete(Response response);
    }

    public FollowUserTask(UserPresenter presenter, FollowUserObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected Response doInBackground(Follow... follows) {
        return presenter.followUser(follows[0]);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (observer != null) {
            observer.followUserComplete(response);
        }
    }
}
