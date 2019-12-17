package fpt.edu.cocshop.Service;

import fpt.edu.cocshop.Retrofit.ConfigApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FBrandService {
    @GET(ConfigApi.Api.BRAND)
    Call<ResponseBody> getPopularBrand(@Header("Authorization") String token, @Query("PageSize") int pageSize, @Query("PageIndex") int pageIndex);
}
