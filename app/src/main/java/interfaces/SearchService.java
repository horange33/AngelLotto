package interfaces;
import model.storesearch.StoreSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchService {

    public static final String DAUM_SEARCH_URL = "https://dapi.kakao.com";

    @Headers({
            "Content-Type:application/x-www-form-urlencoded",
            "Authorization: KakaoAK 3f84bf1dbbd1a65f6e2e84e9a1530977"
    })
    //@Headers("Authorization: KakaoAK {3f84bf1dbbd1a65f6e2e84e9a1530977}")
    @GET("v2/local/search/keyword.json")
    Call<StoreSearch> getStoreData(
            @Query(value = "query", encoded = true) String query,
            @Query(value = "x", encoded = true) String X,
            @Query(value = "y", encoded = true) String Y,
            @Query(value = "radius", encoded = true) String radius
    );
}
