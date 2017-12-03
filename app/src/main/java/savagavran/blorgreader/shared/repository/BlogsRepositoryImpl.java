package savagavran.blorgreader.shared.repository;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import savagavran.blorgreader.shared.RepositoryBlogList;
import savagavran.blorgreader.shared.ServiceApi;
import savagavran.blorgreader.shared.ServiceApiCache;
import savagavran.blorgreader.utils.BlogItem;

public class BlogsRepositoryImpl implements RepositoryBlogList<BlogItem> {

    private ServiceApi mServiceApi;
    private ServiceApiCache mServiceApiCache;

    public BlogsRepositoryImpl(@NonNull ServiceApi serviceApi,
                               @NonNull ServiceApiCache serviceApiCache) {
        mServiceApi = serviceApi;
        mServiceApiCache = serviceApiCache;
    }

    @Override
    public Single<List<BlogItem>> getAllBlogs() {
        return Observable.concat(mServiceApiCache.getCachedBlogs(), mServiceApi.getAllBlogs()
                .doOnNext(blogs -> mServiceApiCache.cacheBlogs(blogs)))
                .firstOrError();
    }
}
