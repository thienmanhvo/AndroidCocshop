package fpt.edu.cocshop.Home_Store_List;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import fpt.edu.cocshop.Model.BaseViewModel;
import fpt.edu.cocshop.Model.PagingResult;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.Retrofit.ClientApi;
import fpt.edu.cocshop.Retrofit.ResponseData;
import fpt.edu.cocshop.Util.Token;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeStoreListModel implements HomeStoreListContract.Model {


    private final String TAG = "MovieListModel";

    @Override
    public void getStoreList(final OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fStoreService().getStore(Token.token, latitude, longitude, pageSize, pageIndex);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<BaseViewModel<PagingResult<Store>>>>() {
                        }.getType();
                        BaseViewModel<PagingResult<Store>> responseData = new Gson().fromJson(result, type);

                        if (responseData != null) {
                            List<Store> list = responseData.getData().getResults();
                            Log.d(TAG, "Number of movies received: " + list.size());
                            onFinishedListener.onFinished(list);
                        } else {
                            onFinishedListener.onFailure("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
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

    @Override
    public void getNearestStoreList(OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude, double radius) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fStoreService().getNearestStore(Token.token, latitude, longitude, radius, pageSize, pageIndex);
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
                            List<Store> list = responseData.getData().getResults();
                            Log.d(TAG, "Number of movies received: " + list.size());
                            onFinishedListener.onFinished(list);
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
