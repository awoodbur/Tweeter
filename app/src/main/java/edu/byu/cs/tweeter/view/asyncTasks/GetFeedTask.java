package edu.byu.cs.tweeter.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.request.FeedRequest;
import edu.byu.cs.tweeter.net.response.FeedResponse;
import edu.byu.cs.tweeter.presenter.FeedPresenter;
import edu.byu.cs.tweeter.view.cache.ImageCache;
import edu.byu.cs.tweeter.view.util.ImageUtils;

/**
 * An {@link AsyncTask} for retrieving tweets for a user.
 */
public class GetFeedTask extends AsyncTask<FeedRequest, Void, FeedResponse> {

    private final FeedPresenter presenter;
    private final GetTweetsObserver observer;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface GetTweetsObserver {
        void tweetsRetrieved(FeedResponse feedResponse);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve tweets.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public GetFeedTask(FeedPresenter presenter, GetTweetsObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    /**
     * The method that is invoked on the background thread to retrieve tweets.
     *
     * @param feedRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected FeedResponse doInBackground(FeedRequest... feedRequests) {
        FeedResponse response = presenter.getFeed(feedRequests[0]);
        loadImages(response);
        return response;
    }

    /**
     * Loads the image associated with each tweet included in the response.
     *
     * @param response the response from the tweet request.
     */
    private void loadImages(FeedResponse response) {
        for(Tweet tweet : response.getTweets()) {

            User user = tweet.getAuthor();

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
     * @param feedResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(FeedResponse feedResponse) {

        if(observer != null) {
            observer.tweetsRetrieved(feedResponse);
        }
    }
}
