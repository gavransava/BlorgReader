package savagavran.blorgreader.main.blogList.di;

import dagger.Module;
import dagger.Provides;
import savagavran.blorgreader.RecyclerAdapterView;
import savagavran.blorgreader.main.blogList.BlogItemAdapter;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.main.blogList.presenter.BlogsPresenter;
import savagavran.blorgreader.shared.RecyclerAdapterModel;
import savagavran.blorgreader.shared.RepositoryBlog;
import savagavran.blorgreader.shared.RepositoryBlogList;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.utils.Blog;
import savagavran.blorgreader.utils.BlogItem;

@BlogsScope
@Module
public class BlogsModule {

    private BlogsContract.BlogsScreen mBlogsScreen;
    private BlogItemAdapter mBlogItemAdapter;

    public BlogsModule(BlogsContract.BlogsScreen screen) {
        mBlogsScreen = screen;
        mBlogItemAdapter = new BlogItemAdapter();
    }

    @Provides
    public BlogsContract.BlogsScreen provideBlogsScreen() {
        return mBlogsScreen;
    }

    @Provides
    public RecyclerAdapterModel<BlogItem> provideBlogItemAdapter() {
        return mBlogItemAdapter;
    }

    @Provides
    public RecyclerAdapterView provideBlogItemAdapterView() {
        return mBlogItemAdapter;
    }

    @Provides
    public BlogsContract.BlogsUserActions provideBlogsUserActions(
            AuthManager mAuthManager,
            RecyclerAdapterModel<BlogItem> recyclerAdapterModel,
            RepositoryBlogList<BlogItem> blogListRepository,
            RepositoryBlog<Blog> blogRepository) {
        return new BlogsPresenter(
                mBlogsScreen,
                recyclerAdapterModel,
                mAuthManager,
                blogListRepository,
                blogRepository);
    }

}
