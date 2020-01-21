package edu.byu.cs.tweeter.view.android.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.view.android.domain.User;
import edu.byu.cs.tweeter.view.android.asyncTasks.LoadImageTask;
import edu.byu.cs.tweeter.view.cache.DataCache;

public class MainActivity extends AppCompatActivity implements LoadImageTask.LoadImageObserver {

    private ImageView userImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Remove this when the login functionality is created to log in and set the user
        User user = new User("Test", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        DataCache.getInstance().setUser(user);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Asynchronously load the user's image
        userImageView = findViewById(R.id.userImage);

        // TODO: Keep this after the above code to set it is replaced by the login functionality
        user = DataCache.getInstance().getUser();

        Drawable userImage = user.getImage();
        if(userImage == null) {
            LoadImageTask loadImageTask = new LoadImageTask(this);
            loadImageTask.execute(DataCache.getInstance().getUser().getImageUrl());
        } else {
            userImageView.setImageDrawable(userImage);
        }

        TextView userName = findViewById(R.id.userName);
        userName.setText(user.getName());

        TextView userHandle = findViewById(R.id.userHandle);
        userHandle.setText(user.getHandle());
    }

    @Override
    public void imageLoadProgressUpdated(Integer progress) {
        // We're just loading one image. No need to indicate progress.
    }

    @Override
    public void imagesLoaded(Drawable[] drawables) {
        DataCache.getInstance().getUser().setImage(drawables[0]);

        if(drawables[0] != null) {
            userImageView.setImageDrawable(drawables[0]);
        }
    }
}