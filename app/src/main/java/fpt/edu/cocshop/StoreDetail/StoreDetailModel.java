package fpt.edu.cocshop.StoreDetail;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Model.BaseViewModel;
import fpt.edu.cocshop.Model.PagingResult;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.Retrofit.ClientApi;
import fpt.edu.cocshop.Store_List.StoreListContract;
import fpt.edu.cocshop.Util.Token;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDetailModel implements StoreDetailContract.Model {

    private final String TAG = "StoreDetailModel";

    @Override
    public void getStoreDetail(StoreDetailContract.Model.OnFinishedListener onFinishedListener, double latitude, double longitude, String brandId) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fStoreService().getStoreDetail(Token.token, brandId, latitude, longitude);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<BaseViewModel<Store>>() {
                        }.getType();
                        BaseViewModel<Store> responseData = new Gson().fromJson(result, type);

                        if (responseData != null) {
                            if (responseData.getData() != null) {
                                Store store = responseData.getData();
                                Log.d(TAG, "Number of movies received: " + store);
                                onFinishedListener.onStoreFinished(store);
                            } else {
                                Log.d(TAG, "Not found");
                                onFinishedListener.onStoreFinished(null);
                            }
                        } else {
                            onFinishedListener.onFailure("Lỗi server");

                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        onFinishedListener.onFailure(e.getMessage());
                    }
                } else {
                    Log.e(TAG, response.toString());
                    onFinishedListener.onFailure(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });
    }
}
