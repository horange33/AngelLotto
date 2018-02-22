package interfaces;

import model.storesearch.StoreSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchService {

    public static final String DAUM_SEARCH_URL = "https://apis.daum.net/";

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("local/v1/search/keyword.json")
    Call<StoreSearch> getStoreData(
            @Query(value = "apikey", encoded = true) String apikey,
            @Query(value = "location", encoded = true) String location,
            @Query(value = "query", encoded = true) String query,
            @Query(value = "radius", encoded = true) String radius
    );
}
