package edu.byu.cs.tweeter.client.view.main.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.view.main.LoginActivity;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.client.presenter.TweetPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.ShareTweetTask;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;

public class TweetDialogFragment extends DialogFragment implements ShareTweetTask.ShareTweetObserver, TweetPresenter.View {

    private static final String TAG = TweetDialogFragment.class.getName();

    private EditText mEditText;
    private TweetPresenter presenter;

    public TweetDialogFragment() {}

    public static TweetDialogFragment newInstance() {
//        TweetDialogFragment frag =
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        frag.setArguments(args);
        return new TweetDialogFragment();
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_tweet, null);
        final EditText tweetTextbox = view.findViewById(R.id.fragment_tweet_textbox);

        presenter = new TweetPresenter(this);

        builder.setTitle(R.string.title_compose_tweet);
        builder.setView(view);

        builder.setPositiveButton(R.string.button_share, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ShareTweetTask shareTweetTask = new ShareTweetTask(presenter, TweetDialogFragment.this);
                ShareTweetRequest request = new ShareTweetRequest(new Tweet(presenter.getCurrentUser(), tweetTextbox.getText().toString()), presenter.getAuthToken());
                shareTweetTask.execute(request);
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void tweetShared(ShareTweetResponse response) {
        Log.d(TAG, response.getMessage());
        try {
            Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void handleException(Exception e) {
        Log.e(TAG, e.getMessage(), e);
        if (e.getMessage() != null && e.getMessage().equals("401 ERROR: Access Denied")) {
            presenter.setAuthToken(null);
            startActivity(LoginActivity.newIntent(getActivity()));
        } else {
            try {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }
}
