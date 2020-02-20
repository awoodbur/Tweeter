package edu.byu.cs.tweeter.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.presenter.UserPresenter;
import edu.byu.cs.tweeter.view.cache.ImageCache;

public class UserActivity extends AppCompatActivity implements UserPresenter.View {

    private UserPresenter presenter;
    private User user;
    private User display_user;


    private static final String EXTRA_USER = "edu.byu.cs.tweeter.view.main.UserActivity.user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        display_user = (User) getIntent().getSerializableExtra(EXTRA_USER);

        presenter = new UserPresenter(this);

        presenter.setDisplayUser(display_user);
        user = presenter.getCurrentUser();

        if (display_user != null) {
            ImageView profile = findViewById(R.id.activity_user_profile);
            profile.setImageDrawable(ImageCache.getInstance().getImageDrawable(display_user));

            TextView name = findViewById(R.id.activity_user_name);
            name.setText(display_user.getName());

            TextView alias = findViewById(R.id.activity_user_alias);
            alias.setText(display_user.getAliasAt());
        }

        Button buttom = findViewById(R.id.activity_user_button);
        if (user.equals(display_user)) {
            buttom.setText(R.string.button_logout);
        } else {
            if (presenter.doesUserFollowUser(user, display_user)) {
                buttom.setText(R.string.button_unfollow);
            } else {
                buttom.setText(R.string.button_follow);
            }
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
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
}
