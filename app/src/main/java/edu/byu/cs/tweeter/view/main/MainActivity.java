package edu.byu.cs.tweeter.view.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.presenter.MainPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.LoadImageTask;
import edu.byu.cs.tweeter.view.asyncTasks.SearchTask;
import edu.byu.cs.tweeter.view.cache.ImageCache;
import edu.byu.cs.tweeter.view.main.adapters.SectionsPagerAdapter;
import edu.byu.cs.tweeter.view.main.fragments.TweetDialogFragment;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class MainActivity extends AppCompatActivity implements SearchTask.SearchObserver, LoadImageTask.LoadImageObserver, MainPresenter.View {

    private MainPresenter presenter;
    private User user;
    private ImageView userImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleIntent(getIntent());

        presenter = new MainPresenter(this);

        user = presenter.getCurrentUser();

        if (user == null) {
            startActivity(LoginActivity.newIntent(this));
        } else {
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            TabLayout tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);

            findViewById(R.id.activity_main_search).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSearchRequested();
                }
            });

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTweetDialog();
                }
            });

            userImageView = findViewById(R.id.activity_main_profile);
            userImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(UserActivity.newIntent(MainActivity.this, user));
                }
            });

            // Asynchronously load the user's image
            LoadImageTask loadImageTask = new LoadImageTask(this);
            loadImageTask.execute(user.getImageUrl());

//        TextView userName = findViewById(R.id.userName);
//        userName.setText(user.getName());
//
//        TextView userAlias = findViewById(R.id.userAlias);
//        userAlias.setText(user.getAlias());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchTask searchTask = new SearchTask(presenter, this);
            searchTask.execute(query);
        }
    }

    private void showTweetDialog() {
        FragmentManager fm = getSupportFragmentManager();
        TweetDialogFragment tweetDialogFragment = TweetDialogFragment.newInstance();
        tweetDialogFragment.show(fm, "fragment_alert");
    }

    @Override
    public void imageLoadProgressUpdated(Integer progress) {
        // We're just loading one image. No need to indicate progress.
    }

    /**
     * A callback that indicates that the image for the user being displayed on this activity has
     * been loaded.
     *
     * @param drawables the drawables (there will only be one).
     */
    @Override
    public void imagesLoaded(Drawable[] drawables) {
        ImageCache.getInstance().cacheImage(user, drawables[0]);

        if(drawables[0] != null) {
            userImageView.setImageDrawable(drawables[0]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setDisplayUser(presenter.getCurrentUser());
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    public void searchComplete(User user) {
        if (user != null) {
            startActivity(UserActivity.newIntent(this, user));
        } else {
            Toast.makeText(this, "Unable to find user.", Toast.LENGTH_SHORT).show();
        }
    }
}