package savagavran.blorgreader.shared.retrofit;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import savagavran.blorgreader.utils.Token;


public interface AuthService {

    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @POST("/login")
    Observable<Token> login(@Body Credentials credentials);
}
