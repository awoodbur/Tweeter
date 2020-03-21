package edu.byu.cs.tweeter.client.view.main.followers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.client.presenter.FollowersPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.GetFollowersTask;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.client.view.main.UserActivity;

public class FollowersFragment extends Fragment implements FollowersPresenter.View {

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    private static final int PAGE_SIZE = 10;

    private FollowersPresenter presenter;

    private FollowersRecyclerViewAdapter followersRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_list, container, false);

        presenter = new FollowersPresenter(this);

        RecyclerView followersRecyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        followersRecyclerView.setLayoutManager(layoutManager);

        followersRecyclerViewAdapter = new FollowersRecyclerViewAdapter();
        followersRecyclerView.setAdapter(followersRecyclerViewAdapter);

        followersRecyclerView.addOnScrollListener(new FollowersRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }

    /**
     * The ViewHolder for the RecyclerView that displays the Followers data.
     */
    private class FollowersHolder extends RecyclerView.ViewHolder {

        private final ImageView userImage;
        private final TextView userAlias;
        private final TextView userName;

        FollowersHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.recycler_item_icon);
            userAlias = itemView.findViewById(R.id.recycler_item_alias);
            userName = itemView.findViewById(R.id.recycler_item_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(UserActivity.newIntent(getActivity(), presenter.getUserByAlias(userAlias.getText().toString().substring(1))));
                }
            });
        }

        void bindUser(User user) {
            userImage.setImageDrawable(ImageCache.getInstance().getImageDrawable(user));
            userAlias.setText(user.getAliasAt());
            userName.setText(user.getName());
        }
    }

    /**
     * The adapter for the RecyclerView that displays the Followers data.
     */
    private class FollowersRecyclerViewAdapter extends RecyclerView.Adapter<FollowersHolder> implements GetFollowersTask.GetFollowersObserver {

        private final List<User> users = new ArrayList<>();

        private User lastFollower;

        private boolean hasMorePages;
        private boolean isLoading = false;

        /**
         * Creates an instance and loads the first page of followers data.
         */
        FollowersRecyclerViewAdapter() {
            loadMoreItems();
        }

        /**
         * Adds new users to the list from which the RecyclerView retrieves the users it displays
         * and notifies the RecyclerView that items have been added.
         *
         * @param newUsers the users to add.
         */
        void addItems(List<User> newUsers) {
            int startInsertPosition = users.size();
            users.addAll(newUsers);
            this.notifyItemRangeInserted(startInsertPosition, newUsers.size());
        }

        /**
         * Adds a single user to the list from which the RecyclerView retrieves the users it
         * displays and notifies the RecyclerView that an item has been added.
         *
         * @param user the user to add.
         */
        void addItem(User user) {
            users.add(user);
            this.notifyItemInserted(users.size() - 1);
        }

        /**
         * Removes a user from the list from which the RecyclerView retrieves the users it displays
         * and notifies the RecyclerView that an item has been removed.
         *
         * @param user the user to remove.
         */
        void removeItem(User user) {
            int position = users.indexOf(user);
            users.remove(position);
            this.notifyItemRemoved(position);
        }

        /**
         *  Creates a view holder for a follower to be displayed in the RecyclerView or for a message
         *  indicating that new rows are being loaded if we are waiting for rows to load.
         *
         * @param parent the parent view.
         * @param viewType the type of the view (ignored in the current implementation).
         * @return the view holder.
         */
        @NonNull
        @Override
        public FollowersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(FollowersFragment.this.getContext());
            View view;

            if(viewType == LOADING_DATA_VIEW) {
                view =layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.recycler_item, parent, false);
            }

            return new FollowersHolder(view);
        }

        /**
         * Binds the follower at the specified position unless we are currently loading new data. If
         * we are loading new data, the display at that position will be the data loading footer.
         *
         * @param followersHolder the ViewHolder to which the follower should be bound.
         * @param position the position (in the list of followers) that contains the follower to be
         *                 bound.
         */
        @Override
        public void onBindViewHolder(@NonNull FollowersHolder followersHolder, int position) {
            if(!isLoading) {
                followersHolder.bindUser(users.get(position));
            }
        }

        /**
         * Returns the current number of followers available for display.
         * @return the number of followers available for display.
         */
        @Override
        public int getItemCount() {
            return users.size();
        }

        /**
         * Returns the type of the view that should be displayed for the item currently at the
         * specified position.
         *
         * @param position the position of the items whose view type is to be returned.
         * @return the view type.
         */
        @Override
        public int getItemViewType(int position) {
            return (position == users.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
        }

        /**
         * Causes the Adapter to display a loading footer and make a request to get more followers
         * data.
         */
        void loadMoreItems() {
            isLoading = true;
            addLoadingFooter();

            GetFollowersTask getFollowersTask = new GetFollowersTask(presenter, this);
            FollowersRequest request = new FollowersRequest(presenter.getDisplayUser(), PAGE_SIZE, lastFollower);
            getFollowersTask.execute(request);
        }

        /**
         * A callback indicating more followers data has been received. Loads the new followers
         * and removes the loading footer.
         *
         * @param followersResponse the asynchronous response to the request to load more items.
         */
        @Override
        public void followersRetrieved(FollowersResponse followersResponse) {
            List<User> followers = followersResponse.getFollowers();

            lastFollower = (followers.size() > 0) ? followers.get(followers.size() -1) : null;
            hasMorePages = followersResponse.getHasMorePages();

            isLoading = false;
            removeLoadingFooter();
            followersRecyclerViewAdapter.addItems(followers);
        }

        /**
         * Adds a dummy user to the list of users so the RecyclerView will display a view (the
         * loading footer view) at the bottom of the list.
         */
        private void addLoadingFooter() {
            addItem(new User("Dummy", "User", ""));
        }

        /**
         * Removes the dummy user from the list of users so the RecyclerView will stop displaying
         * the loading footer at the bottom of the list.
         */
        private void removeLoadingFooter() {
            removeItem(users.get(users.size() - 1));
        }
    }

    /**
     * A scroll listener that detects when the user has scrolled to the bottom of the currently
     * available data.
     */
    private class FollowersRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {

        private final LinearLayoutManager layoutManager;

        /**
         * Creates a new instance.
         *
         * @param layoutManager the layout manager being used by the RecyclerView.
         */
        FollowersRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        /**
         * Determines whether the user has scrolled to the bottom of the currently available data
         * in the RecyclerView and asks the adapter to load more data if the last load request
         * indicated that there was more data to load.
         *
         * @param recyclerView the RecyclerView.
         * @param dx the amount of horizontal scroll.
         * @param dy the amount of vertical scroll.
         */
        @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!followersRecyclerViewAdapter.isLoading && followersRecyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    followersRecyclerViewAdapter.loadMoreItems();
                }
            }
        }
    }
}
