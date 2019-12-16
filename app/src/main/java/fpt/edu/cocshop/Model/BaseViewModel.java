package fpt.edu.cocshop.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.ws.rs.core.Response;

public class BaseViewModel<T> implements Serializable {
    public BaseViewModel() {
    }

    public BaseViewModel(T dataModel) {
        data = dataModel;
    }

    @SerializedName("statusCode")
    public Response.Status statusCode;

    @SerializedName("data")
    public T data;

    @SerializedName("code")
    public String code;
    @SerializedName("description")
    public String description;

    public Response.Status getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Response.Status statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
