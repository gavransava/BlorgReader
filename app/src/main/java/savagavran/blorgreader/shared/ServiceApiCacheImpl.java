package savagavran.blorgreader.shared;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import savagavran.blorgreader.utils.BlogItem;

public class ServiceApiCacheImpl implements ServiceApiCache {

    private List<BlogItem> mCachedLogs;

    public ServiceApiCacheImpl() {
        mCachedLogs = new ArrayList<>();
    }

    public Observable<List<BlogItem>> getCachedBlogs() {
        if (mCachedLogs.isEmpty()) return Observable.empty();
        return Observable.just(mCachedLogs);
    }

    public void cacheBlogs(List<BlogItem> blogs) {
        mCachedLogs.addAll(blogs);
    }
}
