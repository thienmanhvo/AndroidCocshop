package fpt.edu.cocshop.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Promotion implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("discountPercent")
    private double discountPercent;

    public Promotion() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
}
