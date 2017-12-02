package savagavran.blorgreader.main.blogList.view;

import android.content.Intent;
import android.os.Bundle;
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
import savagavran.blorgreader.login.view.LoginActivity;
import savagavran.blorgreader.main.blogList.BlogItemAdapter;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.main.blogList.di.DaggerBlogsComponent;

public class BlogsFragment extends Fragment implements BlogsContract.BlogsScreen {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blogs, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.blogs_list);
        Toolbar mActionBarToolbar = view.findViewById(R.id.toolbar);
        setupToolbar(mActionBarToolbar);
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
        mSwipeRefreshLayout.setOnRefreshListener(() -> mBlogsPresenter.loadBlogs());
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
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingError(String message) {
        getActivity()
                .findViewById(R.id.no_content_available_text)
                .setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void openBlogDetails() {

    }

}
