package savagavran.blorgreader.shared.auth;

import android.annotation.SuppressLint;
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

    private static final String TOKEN = "token";
    private static final String PREFERENCE_NAME = "savagavran.blogreader";

    @NonNull
    private AuthService mAuthClient;
    private String mToken;
    private Context mContext;

    public AuthManagerImpl(@NonNull Context context,
                           @NonNull ConnectivityInterceptor connInterceptor) {

        mAuthClient = createAuthClient(connInterceptor);
        mContext = context;
        mToken = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getString(TOKEN, "");
    }

    @Override
    public Observable<Token> login(Credentials credentials) {
        return mAuthClient.login(credentials)
                .doOnNext(token -> saveTokenToSharedPref(token.getmToken()))
                .subscribeOn(Schedulers.io());
    }

    // Suppress apply instead of commit here.
    // This is executed in the Observable flow above,
    // therefore will not block.
    @SuppressLint("ApplySharedPref")
    private void saveTokenToSharedPref(String token) {
        mToken = token;
        mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
                .putString(TOKEN, mToken)
                .commit();
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
