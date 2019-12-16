package fpt.edu.cocshop.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Store implements Serializable
{
    @SerializedName("name")
    public String name;
    @SerializedName("imagePath")
    public String imagePath;
    @SerializedName("rating")
    public long rating;
    @SerializedName("numberOfRating")
    public long numberOfRating;
    @SerializedName("longitude")
    public double longitude;
    @SerializedName("latitude")
    public double latitude;
    @SerializedName("brandId")
    public String brandId;
    @SerializedName("cate_Id")
    public String cate_Id;
    @SerializedName("totalStore")
    public Integer totalStore;
    @SerializedName("locationName")
    public String locationName;

//    public  BrandViewModel Brand { get; set; }
//
//    public  StoreCategoryViewModel StoreCategory { get; set; }


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getNumberOfRating() {
        return numberOfRating;
    }

    public void setNumberOfRating(long numberOfRating) {
        this.numberOfRating = numberOfRating;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCate_Id() {
        return cate_Id;
    }

    public void setCate_Id(String cate_Id) {
        this.cate_Id = cate_Id;
    }

    public Integer getTotalStore() {
        return totalStore;
    }

    public void setTotalStore(Integer totalStore) {
        this.totalStore = totalStore;
    }
}