package edu.byu.cs.tweeter.view.main.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.view.main.fragments.PlaceholderFragment;
import edu.byu.cs.tweeter.view.main.login.SignInFragment;
import edu.byu.cs.tweeter.view.main.login.SignUpFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages
 * of the Main Activity.
 */
public class LoginPagerAdapter extends FragmentPagerAdapter {

    private static final int SIGNIN_FRAGMENT_POSITION = 0;
    private static final int SIGNUP_FRAGMENT_POSITION = 1;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.signInTabTitle, R.string.signUpTabTitle};
    private final Context mContext;

    public LoginPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case SIGNIN_FRAGMENT_POSITION:
                return new SignInFragment();
            case SIGNUP_FRAGMENT_POSITION:
                return new SignUpFragment();
            default:
                return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 2;
    }
}