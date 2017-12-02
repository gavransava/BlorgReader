package savagavran.blorgreader.main.blogList.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import savagavran.blorgreader.R;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.shared.RecyclerAdapterModel;
import savagavran.blorgreader.shared.Repository;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.utils.BlogItem;

public class BlogsPresenter implements BlogsContract.BlogsUserActions {

    private WeakReference<BlogsContract.BlogsScreen> mBlogsScreen;
    private RecyclerAdapterModel<BlogItem> mBlogItemAdapterModel;
    private AuthManager mAuthManager;
    private Repository<BlogItem> mBlogItemRepository;

    public BlogsPresenter(@NonNull BlogsContract.BlogsScreen blogsScreen,
                          @NonNull RecyclerAdapterModel<BlogItem> recyclerAdapterModel,
                          @NonNull AuthManager authManager,
                          @NonNull Repository<BlogItem> blogItemRepository) {
        mBlogsScreen = new WeakReference<>(blogsScreen);
        mBlogItemAdapterModel = recyclerAdapterModel;
        mAuthManager = authManager;
        mBlogItemRepository = blogItemRepository;
    }

    @Override
    public void loadBlogs() {
        BlogsContract.BlogsScreen blogsScreen = mBlogsScreen.get();
        if(blogsScreen != null) {
            mBlogItemRepository.getAllBlogs()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(blogs -> {
                mBlogItemAdapterModel.setItems(blogs);
                blogsScreen.showBlogs();
            }, throwable -> {
                throwable.printStackTrace();
                blogsScreen.showLoadingError(throwable.getMessage());}
            );
        }
    }

    @Override
    public void onScreenLaunched(Context context) {
        mAuthManager.authentication()
                .flatMap(authenticated -> {
                    if(!authenticated) {
                        return Observable.error(
                                new Throwable(context.getString(R.string.authentication_required)));
                    } return Observable.just(true);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authenticated -> mBlogsScreen.get().onAuthenticationConfirmed(),
                        throwable -> mBlogsScreen.get().onAuthenticationRequired());
    }

    @Override
    public void onBlogsDetailsClicked(int position) {
        mBlogItemAdapterModel.getItem(position).getId();
    }
}