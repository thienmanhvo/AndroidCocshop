package fpt.edu.cocshop.Service;

import fpt.edu.cocshop.Retrofit.ConfigApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FStoreService {
    @GET(ConfigApi.Api.STORE)
    Call<ResponseBody> getStore(@Header("Authorization") String token, @Query("Latitude") double latitude, @Query("Longitude") double longitude, @Query("PageSize") int pageSize, @Query("PageIndex") int pageIndex);

    @GET(ConfigApi.Api.GET_STORE_DETAIL)
    Call<ResponseBody> getStoreDetail(@Header("Authorization") String token, @Query("Latitude") double latitude, @Query("Longitude") double longitude, String StoreId);

    @GET(ConfigApi.Api.STORE)
    Call<ResponseBody> getStoreByBrandId(@Header("Authorization") String token, @Query("Latitude") double latitude, @Query("Longitude") double longitude, @Query("PageSize") int pageSize, @Query("PageIndex") int pageIndex, @Query("Filter[brandId(e)]") String brandId);

    @GET(ConfigApi.Api.GET_NEAREST_STORE)
    Call<ResponseBody> getNearestStore(@Header("Authorization") String token, @Query("Latitude") double latitude, @Query("Longitude") double longitude, @Query("Radius") double radius, @Query("PageSize") int pageSize, @Query("PageIndex") int pageIndex);

    @GET(ConfigApi.Api.GET_STORE_BY_ID)
    Call<ResponseBody> getStoreById(@Header("Authorization") String token, @Path("id") String id);

    @GET(ConfigApi.Api.GET_TOP_STORE)
    Call<ResponseBody> getTopStore(@Header("Authorization") String token, @Query("PageSize") int pageSize, @Query("PageIndex") int pageIndex, @Query("Latitude") double latitude, @Query("Longitude") double longitude);

//    @GET(ConfigApi.Api.PRODUCT2)
//    Call<ResponseBody> getProduct2(@Header("Authorization") String token, @Query("PageSize") int pageSize, @Query("PageIndex") int pageIndex);

//    @GET(ConfigApi.Api.SEARCH)
//    Call<ResponseBody> search(@Query("Filter[productName(e)]") String id ,@Header("Authorization") String token );
}
