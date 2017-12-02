package savagavran.blorgreader.shared.repository;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import savagavran.blorgreader.shared.RepositoryBlog;
import savagavran.blorgreader.shared.RepositorySpecification;
import savagavran.blorgreader.shared.ServiceApi;
import savagavran.blorgreader.utils.Blog;

public class BlogRepositoryImpl implements RepositoryBlog<Blog> {

    private ServiceApi mServiceApi;

    public BlogRepositoryImpl(@NonNull ServiceApi serviceApi) {
        this.mServiceApi = serviceApi;
    }

    @Override
    public Observable<Blog> getBlog(RepositorySpecification repositorySpecification) {
        return mServiceApi.getBlog(repositorySpecification.getID());
    }
}