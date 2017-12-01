package savagavran.blorgreader.shared;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import savagavran.blorgreader.shared.auth.ConnectivityInterceptor;
import savagavran.blorgreader.shared.retrofit.ApiServiceEndpoints;
import savagavran.blorgreader.shared.retrofit.AuthConstants;
import savagavran.blorgreader.utils.Blog;
import savagavran.blorgreader.utils.BlogItem;

import static savagavran.blorgreader.shared.auth.AuthManagerImpl.PREFERENCE_NAME;
import static savagavran.blorgreader.shared.auth.AuthManagerImpl.TOKEN;

public class ServiceApiImpl implements ServiceApi {

    private final String mToken;
    @NonNull
    private ApiServiceEndpoints mApiServiceEndpoints;

    public ServiceApiImpl(@NonNull Context context,
                          @NonNull ConnectivityInterceptor connInterceptor) {
        mApiServiceEndpoints = createApiServiceEndpoints(connInterceptor);
        mToken = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getString(TOKEN, "");
    }

    @Override
    public Observable<List<BlogItem>> getAllBlogs() {
        return mApiServiceEndpoints.getAllBlogs(mToken)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Blog> getBlog(String id) {
        return mApiServiceEndpoints.getBlog(mToken, id)
                .subscribeOn(Schedulers.io());
    }

    private ApiServiceEndpoints createApiServiceEndpoints(ConnectivityInterceptor connInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(connInterceptor);
        return new Retrofit.Builder()
                .baseUrl(AuthConstants.BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServiceEndpoints.class);
    }
}
