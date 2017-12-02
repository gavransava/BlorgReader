package savagavran.blorgreader.shared;


import java.util.List;

import io.reactivex.Observable;

public interface RepositoryBlogList<T> {

    Observable<List<T>> getAllBlogs();
}
