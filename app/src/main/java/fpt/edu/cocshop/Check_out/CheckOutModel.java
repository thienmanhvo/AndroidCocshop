package fpt.edu.cocshop.Check_out;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import fpt.edu.cocshop.Model.CartObj;
import fpt.edu.cocshop.Model.ItemOrder;
import fpt.edu.cocshop.Model.Product;
import fpt.edu.cocshop.Retrofit.ClientApi;
import fpt.edu.cocshop.StoreDetail.StoreDetailContract;
import fpt.edu.cocshop.Util.Token;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutModel implements CheckOutContract.Model {


    private final String TAG = "CheckOutModel";

    @Override
    public void checkOut(CheckOutContract.Model.OnFinishedListener onFinishedListener, double latitude, double longitude, CartObj cartObj, String storeId) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();

        try {
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            jsonObject.put("storeId", storeId);

            for (Map.Entry me : cartObj.getCart().entrySet()) {
                jsonObject1.put("id", ((ItemOrder) me.getValue()).getId());
                jsonObject1.put("quantity", ((ItemOrder) me.getValue()).getQuantityInCart());
                if (cartObj.getDiscount() != null) {
                    jsonObject1.put("price", ((ItemOrder) me.getValue()).getPrice() * cartObj.getDiscount());
                } else {
                    jsonObject1.put("price", ((ItemOrder) me.getValue()).getPrice());
                }
                jsonArray.put(jsonObject1);
            }
            jsonObject.put("products", jsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fOrderService().createOrder(Token.token, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        onFinishedListener.onCheckoutFinished();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        onFinishedListener.onFailure(e.getMessage());
                    }
                } else {
                    onFinishedListener.onFailure("Failed");
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

