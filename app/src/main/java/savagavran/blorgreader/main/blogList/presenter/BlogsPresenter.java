package savagavran.blorgreader.main.blogList.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import savagavran.blorgreader.R;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.shared.auth.AuthManager;

public class BlogsPresenter implements BlogsContract.BlogsUserActions {

    public BlogsPresenter(BlogsContract.BlogsScreen mBlogsScreen, AuthManager mAuthManager) {
        this.mBlogsScreen = new WeakReference<>(mBlogsScreen);
        this.mAuthManager = mAuthManager;
    }

    private WeakReference<BlogsContract.BlogsScreen> mBlogsScreen;
    private AuthManager mAuthManager;

    @Override
    public void loadBlogs() {

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
                .subscribe(authenticated -> mBlogsScreen.get().showBlogs(),
                        throwable -> mBlogsScreen.get().onAuthenticationRequired());
    }

    @Override
    public void onBlogsDetailsClicked(int position) {

    }
}