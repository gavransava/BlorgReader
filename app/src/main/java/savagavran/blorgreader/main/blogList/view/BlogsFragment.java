package savagavran.blorgreader.main.blogList.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import savagavran.blorgreader.App;
import savagavran.blorgreader.R;
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

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        Toolbar mActionBarToolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(mActionBarToolbar);
        activity.getSupportActionBar().setTitle(getString(R.string.blog_list));

        App app = App.getAppContext(getActivity());
        DaggerBlogsComponent
                .builder()
                .appComponent(app.getComponent())
                .blogsModule(app.getBlogsModule(this))
                .build()
                .inject(this);

        return view;
    }

    @Override
    public void onAuthenticationConfirmed() {

    }

    @Override
    public void onAuthenticationRequired() {

    }

    @Override
    public void showBlogs() {

    }

    @Override
    public void openBlogDetails() {

    }
}
