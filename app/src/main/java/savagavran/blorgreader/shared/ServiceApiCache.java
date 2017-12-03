package savagavran.blorgreader.shared;

import java.util.List;

import io.reactivex.Observable;
import savagavran.blorgreader.utils.BlogItem;

public interface ServiceApiCache {

    Observable<List<BlogItem>> getCachedBlogs();

    void cacheBlogs(List<BlogItem> blogs);
}
