package savagavran.blorgreader.main.blogList.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import savagavran.blorgreader.App;
import savagavran.blorgreader.R;
import savagavran.blorgreader.RecyclerAdapterView;
import savagavran.blorgreader.blogDetails.BlogActivity;
import savagavran.blorgreader.blogDetails.view.BlogFragment;
import savagavran.blorgreader.login.view.LoginActivity;
import savagavran.blorgreader.main.blogList.BlogItemAdapter;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.main.blogList.di.DaggerBlogsComponent;

public class BlogsFragment extends Fragment implements BlogsContract.BlogsScreen {

    private RecyclerView mRecyclerView;
    private Parcelable mListState;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static final String LIST_STATE = "ListState";

    @Inject
    BlogsContract.BlogsUserActions mBlogsPresenter;

    @Inject
    RecyclerAdapterView mRecyclerViewAdapter;


    public BlogsFragment() {
        // Required empty public constructor
    }

    public static BlogsFragment newInstance() {
        BlogsFragment fragment = new BlogsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(LIST_STATE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LIST_STATE, mRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blogs, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.blogs_list);
        Toolbar actionBarToolbar = view.findViewById(R.id.toolbar);
        setupToolbar(actionBarToolbar);
        setupRefresh();

        App app = App.getAppContext(getActivity());
        DaggerBlogsComponent
                .builder()
                .appComponent(app.getComponent())
                .blogsModule(app.getBlogsModule(this))
                .build()
                .inject(this);

        setupRecyclerView();
        mBlogsPresenter.onScreenLaunched(this.getContext());

        return view;
    }

    private void setupRecyclerView() {
        mRecyclerView.setAdapter((BlogItemAdapter) mRecyclerViewAdapter);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter.setItemListener((position ->
                mBlogsPresenter.onBlogsDetailsClicked(position)));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setupToolbar(Toolbar mActionBarToolbar) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mActionBarToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.blog_list));
        }
    }

    private void setupRefresh() {
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
                    mBlogsPresenter.loadBlogs();
                    mListState = null;
                }
        );
    }

    @Override
    public void onAuthenticationConfirmed() {
        mBlogsPresenter.loadBlogs();
    }

    @Override
    public void onAuthenticationRequired() {
        startActivity(new Intent(this.getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void showBlogs() {
        resetRefresh();
        mRecyclerView.setVisibility(View.VISIBLE);
        if (mListState != null) {
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mListState);
        }
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingError(String message) {
        resetRefresh();
        getActivity()
                .findViewById(R.id.no_content_available_text)
                .setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void openBlogDetails(String htmlContent) {
        Intent intent = new Intent(getContext(), BlogActivity.class);
        intent.putExtra(BlogFragment.HTML_CONTENT, htmlContent);
        startActivity(intent);
    }

    private void resetRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
