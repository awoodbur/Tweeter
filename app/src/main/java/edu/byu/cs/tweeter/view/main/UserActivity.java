package edu.byu.cs.tweeter.view.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.presenter.UserPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.LoadImageTask;
import edu.byu.cs.tweeter.view.cache.ImageCache;

public class UserActivity extends AppCompatActivity implements LoadImageTask.LoadImageObserver, UserPresenter.View {

    private UserPresenter presenter;
    private User user;
    private User display_user;
    private ImageView userImageView;


    private static final String EXTRA_USER = "edu.byu.cs.tweeter.view.main.UserActivity.user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        display_user = (User) getIntent().getSerializableExtra(EXTRA_USER);

        presenter = new UserPresenter(this);

        user = presenter.getCurrentUser();

        if (display_user != null) {
            userImageView = findViewById(R.id.activity_main_profile);

            // Asynchronously load the user's image
//            LoadImageTask loadImageTask = new LoadImageTask(this);
//            loadImageTask.execute(display_user.getImageUrl());

            TextView name = findViewById(R.id.activity_user_name);
            name.setText(getString(R.string.text_name_full, display_user.getFirstName(), display_user.getLastName()));

            TextView handle = findViewById(R.id.activity_user_handle);
            handle.setText(getString(R.string.text_handle, display_user.getAlias()));
        }

        Button buttom = findViewById(R.id.activity_user_button);
        if (user.equals(display_user)) {
            buttom.setText(R.string.button_logout);
        } else {
            buttom.setText(R.string.button_follow);
        }
    }

    public static Intent newIntent(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
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
        ImageCache.getInstance().cacheImage(display_user, drawables[0]);

        if(drawables[0] != null) {
            userImageView.setImageDrawable(drawables[0]);
        }
    }
}
