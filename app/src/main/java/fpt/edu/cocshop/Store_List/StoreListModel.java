package fpt.edu.cocshop.Store_List;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Home_Store_List.HomeStoreListContract;
import fpt.edu.cocshop.Model.BaseViewModel;
import fpt.edu.cocshop.Model.PagingResult;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.Retrofit.ClientApi;
import fpt.edu.cocshop.Util.Token;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreListModel implements StoreListContract.Model {


    private final String TAG = "StoreListModel";

    @Override
    public void getStoreList(StoreListContract.Model.OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude, String brandId) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fStoreService().getStoreByBrandId(Token.token, latitude, longitude, pageSize, pageIndex, brandId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<BaseViewModel<PagingResult<Store>>>() {
                        }.getType();
                        BaseViewModel<PagingResult<Store>> responseData = new Gson().fromJson(result, type);

                        if (responseData != null) {
                            if (responseData.getData() != null) {
                                List<Store> list = responseData.getData().getResults();
                                Log.d(TAG, "Number of movies received: " + list.size());
                                onFinishedListener.onStoreFinished(list);
                            } else {
                                Log.d(TAG, "Empty List");
                                onFinishedListener.onStoreFinished(new ArrayList<>());
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
