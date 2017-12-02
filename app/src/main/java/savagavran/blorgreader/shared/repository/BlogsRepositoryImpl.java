package savagavran.blorgreader.shared.repository;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import savagavran.blorgreader.shared.Repository;
import savagavran.blorgreader.shared.ServiceApi;
import savagavran.blorgreader.utils.BlogItem;

public class BlogsRepositoryImpl implements Repository<BlogItem> {

    private ServiceApi mServiceApi;

    public BlogsRepositoryImpl(@NonNull ServiceApi serviceApi) {
        this.mServiceApi = serviceApi;
    }

    @Override
    public Observable<List<BlogItem>> getAllBlogs() {
        return mServiceApi.getAllBlogs();
    }
}
