package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.net.response.TweetResponse;
import edu.byu.cs.tweeter.presenter.TweetPresenter;

public class ShareTweetTask extends AsyncTask<Tweet, Void, TweetResponse> {

    private final TweetPresenter presenter;
    private final ShareTweetObserver observer;

    public interface ShareTweetObserver {
        void tweetShared(TweetResponse response);
    }

    public ShareTweetTask(TweetPresenter presenter, ShareTweetObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected TweetResponse doInBackground(Tweet... tweets) {
        TweetResponse response = presenter.shareTweet(tweets[0]);
        return response;
    }

    @Override
    protected void onPostExecute(TweetResponse response) {
        if (observer != null) {
            observer.tweetShared(response);
        }
    }
}
