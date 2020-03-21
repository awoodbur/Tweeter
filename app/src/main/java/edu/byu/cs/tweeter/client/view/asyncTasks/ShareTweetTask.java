package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.client.presenter.TweetPresenter;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;

public class ShareTweetTask extends AsyncTask<ShareTweetRequest, Void, ShareTweetResponse> {

    private final TweetPresenter presenter;
    private final ShareTweetObserver observer;

    private Exception exception;

    public interface ShareTweetObserver {
        void tweetShared(ShareTweetResponse response);
        void handleException(Exception e);
    }

    public ShareTweetTask(TweetPresenter presenter, ShareTweetObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected ShareTweetResponse doInBackground(ShareTweetRequest... shareTweetRequests) {
        ShareTweetResponse response = null;
        try {
            response = presenter.shareTweet(shareTweetRequests[0]);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(ShareTweetResponse response) {
        if (observer != null) {
            if (exception == null) {
                observer.tweetShared(response);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
