package edu.byu.cs.tweeter.view.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.net.response.TweetResponse;
import edu.byu.cs.tweeter.presenter.TweetPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.ShareTweetTask;

public class TweetDialogFragment extends DialogFragment implements ShareTweetTask.ShareTweetObserver, TweetPresenter.View {

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
                Tweet tweet = new Tweet(presenter.getCurrentUser(), tweetTextbox.getText().toString(), "Feb 20");
                shareTweetTask.execute(tweet);
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
    public void tweetShared(TweetResponse response) {
        Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_tweet, container);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        mEditText = view.findViewById(R.id.fragment_tweet_textbox);
//
//        String title = getArguments().getString("title", "Enter Name");
//        getDialog().setTitle(title);
//
//        mEditText.requestFocus();
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//    }
}
