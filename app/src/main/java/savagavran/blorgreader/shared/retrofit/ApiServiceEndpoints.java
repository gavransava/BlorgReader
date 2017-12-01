package savagavran.blorgreader.shared.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import savagavran.blorgreader.utils.Blog;
import savagavran.blorgreader.utils.BlogItem;

public interface ApiServiceEndpoints {

    @Headers("Accept: application/json")
    @GET("/blogs")
    Observable<List<BlogItem>> getAllBlogs(@Header("X-Authorize") String token);

    @Headers("Accept: application/json")
    @GET("/blogs")
    Observable<Blog> getBlog(@Header("X-Authorize") String token,
                             @Query(value = "id") String id);
}
