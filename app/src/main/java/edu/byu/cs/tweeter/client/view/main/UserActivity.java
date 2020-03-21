package edu.byu.cs.tweeter.client.view.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.client.presenter.UserPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.FollowUserTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.LoadImageTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.LogoutTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.UnfollowUserTask;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.client.view.main.adapters.UserPagerAdapter;

public class UserActivity extends AppCompatActivity implements LoadImageTask.LoadImageObserver, FollowUserTask.FollowUserObserver, UnfollowUserTask.UnfollowUserObserver, LogoutTask.LogoutObserver, UserPresenter.View {

    private UserPresenter presenter;
    private User current_user;
    private User display_user;
    private ImageView profileImage;

    private Button mButton;

    private static final String EXTRA_USER = "edu.byu.cs.tweeter.client.view.main.UserActivity.user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        display_user = (User) getIntent().getSerializableExtra(EXTRA_USER);

        presenter = new UserPresenter(this);

        presenter.setDisplayUser(display_user);
        current_user = presenter.getCurrentUser();

        if (display_user != null) {
            profileImage = findViewById(R.id.activity_user_profile);
            LoadImageTask loadImageTask = new LoadImageTask(this);
            loadImageTask.execute(display_user.getImageUrl());

            TextView name = findViewById(R.id.activity_user_name);
            name.setText(display_user.getName());

            TextView alias = findViewById(R.id.activity_user_alias);
            alias.setText(display_user.getAliasAt());
        }

        mButton = findViewById(R.id.activity_user_button);
        if (current_user.equals(display_user)) {
            mButton.setText(R.string.button_logout);
        } else {
            if (presenter.doesUserFollowUser(current_user, display_user)) {
                mButton.setText(R.string.button_unfollow);
            } else {
                mButton.setText(R.string.button_follow);
            }
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = mButton.getText().toString();
                if (type.equals("Logout")) {
//                    presenter.setCurrentUser(null);
//                    startActivity(LoginActivity.newIntent(UserActivity.this));
                    LogoutTask logoutTask = new LogoutTask(presenter, UserActivity.this);
                    logoutTask.execute(current_user);
                }
                else if (type.equals("Follow")) {
                    FollowUserTask followUserTask = new FollowUserTask(presenter, UserActivity.this);
                    Follow follow = new Follow(current_user, display_user);
                    followUserTask.execute(follow);
                }
                else if (type.equals("Unfollow")) {
                    UnfollowUserTask unfollowUserTask = new UnfollowUserTask(presenter, UserActivity.this);
                    Follow follow = new Follow(current_user, display_user);
                    unfollowUserTask.execute(follow);                }
                else {
                    // Error
                }
            }
        });

        UserPagerAdapter userPagerAdapter = new UserPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(userPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setDisplayUser(display_user);
    }

    public static Intent newIntent(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    @Override
    public void logoutComplete(Response response) {
        startActivity(LoginActivity.newIntent(UserActivity.this));
    }

    @Override
    public void followUserComplete(Response response) {
        mButton.setText(R.string.button_unfollow);
    }

    @Override
    public void unfollowUserComplete(Response response) {
        mButton.setText(R.string.button_follow);
    }

    @Override
    public void imageLoadProgressUpdated(Integer progress) {
        // We're just loading one image. No need to indicate progress.
    }

    @Override
    public void imagesLoaded(Drawable[] drawables) {
        ImageCache.getInstance().cacheImage(display_user, drawables[0]);

        if(drawables[0] != null) {
            profileImage.setImageDrawable(drawables[0]);
        }
    }
}
