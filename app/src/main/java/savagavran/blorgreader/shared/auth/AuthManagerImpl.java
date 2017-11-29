package savagavran.blorgreader.shared.auth;

import android.content.Context;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import savagavran.blorgreader.shared.retrofit.AuthConstants;
import savagavran.blorgreader.shared.retrofit.AuthService;
import savagavran.blorgreader.shared.retrofit.Credentials;
import savagavran.blorgreader.utils.Token;

public class AuthManagerImpl implements AuthManager {

    @NonNull
    private AuthService mAuthClient;
    private String mToken;

    public AuthManagerImpl(@NonNull Context context,
                           @NonNull ConnectivityInterceptor connInterceptor) {

        mAuthClient = createAuthClient(connInterceptor);
    }

    @Override
    public Observable<Token> login(Credentials credentials) {
        return mAuthClient.login(credentials)
                .doOnNext(token -> saveTokenToSharedPref(token.getmToken()))
                .subscribeOn(Schedulers.io());
    }

    private void saveTokenToSharedPref(String token) {
        mToken = token;
        //save to shared pref
    }

    @Override
    public Observable<Boolean> authentication() {
        return Observable.just(!mToken.isEmpty());
    }

    @NonNull
    private AuthService createAuthClient(ConnectivityInterceptor connInterceptor) {
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
                .create(AuthService.class);
    }
}
