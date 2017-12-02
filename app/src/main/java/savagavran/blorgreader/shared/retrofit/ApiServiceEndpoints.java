package savagavran.blorgreader.shared.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import savagavran.blorgreader.utils.Blog;
import savagavran.blorgreader.utils.BlogItem;

public interface ApiServiceEndpoints {

    @Headers("Accept: application/json")
    @GET("/blogs")
    Observable<List<BlogItem>> getAllBlogs(@Header("X-Authorize") String token);

    @Headers("Accept: application/json")
    @GET("/blogs/{BLOG_ID}")
    Observable<Blog> getBlog(@Header("X-Authorize") String token,
                             @Path("BLOG_ID") String id);
}
