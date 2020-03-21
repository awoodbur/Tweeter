package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;

public class CheckFollowTask extends AsyncTask<CheckFollowRequest, Void, CheckFollowResponse> {

    private final Presenter presenter;
    private final CheckFollowObserver observer;

    private Exception exception;

    public interface CheckFollowObserver {
        void checkUserComplete(CheckFollowResponse response);
        void handleException(Exception e);
    }

    public CheckFollowTask(Presenter presenter, CheckFollowObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected CheckFollowResponse doInBackground(CheckFollowRequest... checkFollowRequests) {
        CheckFollowResponse response = null;
        try {
            response = presenter.checkFollow(checkFollowRequests[0]);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(CheckFollowResponse response) {
        if (observer != null) {
            if (exception == null) {
                observer.checkUserComplete(response);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
