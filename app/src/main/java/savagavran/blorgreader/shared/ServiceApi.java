package savagavran.blorgreader.shared;

import java.util.List;

import io.reactivex.Observable;
import savagavran.blorgreader.utils.Blog;
import savagavran.blorgreader.utils.BlogItem;

public interface ServiceApi {

    Observable<List<BlogItem>> getAllBlogs();

    Observable<Blog> getBlog(String id);
}
