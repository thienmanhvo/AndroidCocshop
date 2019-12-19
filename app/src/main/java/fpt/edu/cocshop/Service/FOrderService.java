package fpt.edu.cocshop.Service;


import fpt.edu.cocshop.Retrofit.ConfigApi;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FOrderService {
    @POST(ConfigApi.Api.ORDER)
    Call<ResponseBody> createOrder(@Header("Authorization") String token, @Body RequestBody body);

    @GET(ConfigApi.Api.ORDER)
    Call<ResponseBody> getOrder(@Header("Authorization") String token, @Query("Filter[Status(e)]") String status, @Query("SortBy") String sortBy);
}
