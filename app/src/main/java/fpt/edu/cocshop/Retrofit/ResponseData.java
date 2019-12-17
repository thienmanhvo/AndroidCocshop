package fpt.edu.cocshop.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseData(T data) {
        this.data = data;
    }
}
