package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.ShareTweetServiceProxy;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.service.ShareTweetService;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;

public class TweetPresenter extends Presenter {

    private final View view;

    public interface View {

    }

    public TweetPresenter(View view) { this.view = view; }

    public ShareTweetResponse shareTweet(ShareTweetRequest request) throws IOException {
        ShareTweetService service = new ShareTweetServiceProxy();
        return service.shareTweet(request);
    }
}
