package savagavran.blorgreader.main.blogList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import savagavran.blorgreader.App;
import savagavran.blorgreader.R;
import savagavran.blorgreader.login.view.LoginActivity;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.main.blogList.di.DaggerBlogsComponent;

public class BlogsFragment extends Fragment  implements BlogsContract.BlogsScreen{

    private RecyclerView mRecyclerView;

    @Inject
    BlogsContract.BlogsUserActions mBlogsPresenter;


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
        Toolbar mActionBarToolbar = view.findViewById(R.id.toolbar);

        setupToolbar(mActionBarToolbar);

        App app = App.getAppContext(getActivity());
        DaggerBlogsComponent
                .builder()
                .appComponent(app.getComponent())
                .blogsModule(app.getBlogsModule(this))
                .build()
                .inject(this);

        mBlogsPresenter.onScreenLaunched(this.getContext());

        return view;
    }

    private void setupToolbar(Toolbar mActionBarToolbar) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mActionBarToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(getString(R.string.blog_list));
        }
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

    }

    @Override
    public void openBlogDetails() {

    }
}
