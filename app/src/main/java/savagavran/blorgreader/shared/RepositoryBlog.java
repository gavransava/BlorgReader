package savagavran.blorgreader.shared;

import io.reactivex.Observable;

public interface RepositoryBlog<T> {
    Observable<T> getBlog(RepositorySpecification repositorySpecification);
}
