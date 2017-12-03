package savagavran.blorgreader.shared;


import java.util.List;

import io.reactivex.Single;

public interface RepositoryBlogList<T> {

    Single<List<T>> getAllBlogs();
}
