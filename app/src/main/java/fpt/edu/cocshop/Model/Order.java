package fpt.edu.cocshop.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {
    @SerializedName("id")
    public String id;
    @SerializedName("createdUserId")
    public String createdUserId;
    @SerializedName("totalPrice")
    public Double totalPrice;
    @SerializedName("totalQuantity")
    public Integer totalQuantity;
    @SerializedName("status")
    public String status;
    @SerializedName("deliveryUserId")
    public String deliveryUserId;
    @SerializedName("deliveryToName")
    public String deliveryToName;
    @SerializedName("deliveryToLatitude")
    public Double deliveryToLatitude;
    @SerializedName("deliveryToLongitude")
    public Double deliveryToLongitude;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryUserId() {
        return deliveryUserId;
    }

    public void setDeliveryUserId(String deliveryUserId) {
        this.deliveryUserId = deliveryUserId;
    }

    public String getDeliveryToName() {
        return deliveryToName;
    }

    public void setDeliveryToName(String deliveryToName) {
        this.deliveryToName = deliveryToName;
    }

    public Double getDeliveryToLatitude() {
        return deliveryToLatitude;
    }

    public void setDeliveryToLatitude(Double deliveryToLatitude) {
        this.deliveryToLatitude = deliveryToLatitude;
    }

    public Double getDeliveryToLongitude() {
        return deliveryToLongitude;
    }

    public void setDeliveryToLongitude(Double deliveryToLongitude) {
        this.deliveryToLongitude = deliveryToLongitude;
    }
}
