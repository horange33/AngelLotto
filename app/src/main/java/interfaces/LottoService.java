package interfaces;

import model.storelist.Pojo;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LottoService {

    public static final String LOTTO_URL = "http://www.nlotto.co.kr/";

    @Headers({"Content-Type:application/x-www-form-urlencoded; charset=euc-kr"
            ,"Cache-Control: no-cache"})
    @FormUrlEncoded
    @POST("store.do?method=sellerInfo645Result")
    Call<Pojo> getData(
//            @Body RequestBody data
            @Field(value = "nowPage", encoded = true) String nowPage,
            @Field(value = "rtlrSttus", encoded = true) String rtlrSttus,
            @Field(value = "searchType", encoded = true) String searchType,
            @Field(value = "sltSIDO", encoded = true) String sltSIDO,
            @Field(value = "sltGUGUN", encoded = true) String sltGUGUN

    );
}
