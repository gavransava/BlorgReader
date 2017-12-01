package savagavran.blorgreader.main.blogList.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import savagavran.blorgreader.R;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.shared.ServiceApi;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.utils.BlogItem;

public class BlogsPresenter implements BlogsContract.BlogsUserActions {

    private WeakReference<BlogsContract.BlogsScreen> mBlogsScreen;
    private AuthManager mAuthManager;
    private ServiceApi mServiceApi;
    private List<BlogItem> testBlogs;


    public BlogsPresenter(BlogsContract.BlogsScreen blogsScreen, AuthManager authManager,
                          ServiceApi serviceApi) {
        mBlogsScreen = new WeakReference<>(blogsScreen);
        mAuthManager = authManager;
        mServiceApi = serviceApi;
    }

    @Override
    public void loadBlogs() {
        BlogsContract.BlogsScreen blogsScreen = mBlogsScreen.get();
        if(blogsScreen != null) {
            mServiceApi.getAllBlogs()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(blogs -> {
                testBlogs = blogs;
                blogsScreen.showBlogs();
            });
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

    }
}