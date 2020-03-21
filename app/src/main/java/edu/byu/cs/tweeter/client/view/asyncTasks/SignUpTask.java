package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.SignUpPresenter;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

public class SignUpTask extends AsyncTask<SignUpRequest, Void, SignUpResponse> {

    private final SignUpPresenter presenter;
    private final SignUpObserver observer;

    private Exception exception;

    public interface SignUpObserver {
        void signUpComplete(SignUpResponse response);
        void handleException(Exception e);
    }

    public SignUpTask(SignUpPresenter presenter, SignUpObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected SignUpResponse doInBackground(SignUpRequest... signUpRequests) {
        SignUpResponse response = null;
        try {
            response = presenter.signUp(signUpRequests[0]);
        } catch(IOException e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(SignUpResponse response) {
        if (observer != null) {
            if (exception == null) {
                observer.signUpComplete(response);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
