package fpt.edu.cocshop.OnGoing_Order;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Model.BaseViewModel;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Order;
import fpt.edu.cocshop.Model.PagingResult;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.Retrofit.ClientApi;
import fpt.edu.cocshop.Util.Token;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnGoingOrderModel implements OnGoingOrderContract.Model {

    private final String TAG = "OnGoingOrderModel";

    @Override
    public void getOrder(OnFinishedListener onFinishedListener) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fOrderService().getOrder(Token.token, "Submitted", "-createdAt");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<BaseViewModel<PagingResult<Order>>>() {
                        }.getType();
                        BaseViewModel<PagingResult<Order>> responseData = new Gson().fromJson(result, type);

                        if (responseData != null) {
                            if (responseData.getData() != null) {
                                List<Order> list = responseData.getData().getResults();
                                Log.d(TAG, "Number of movies received: " + list.size());
                                onFinishedListener.onFinished(list.get(0));
                            } else {
                                Log.d(TAG, "Empty List");
                                onFinishedListener.onFinished(null);
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
