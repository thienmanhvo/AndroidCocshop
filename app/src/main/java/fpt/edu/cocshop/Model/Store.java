package fpt.edu.cocshop.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Store implements Serializable {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("imagePath")
    public String imagePath;
    @SerializedName("rating")
    public Long rating;
    @SerializedName("numberOfRating")
    public Long numberOfRating;
    @SerializedName("longitude")
    public Double longitude;
    @SerializedName("latitude")
    public Double latitude;
    @SerializedName("brandId")
    public String brandId;
    @SerializedName("cate_Id")
    public String cate_Id;
    @SerializedName("totalStore")
    public Integer totalStore;
    @SerializedName("locationName")
    public String locationName;
    @SerializedName("averagePrice")
    public Double averagePrice;
    @SerializedName("distance")
    public Double distance;
    @SerializedName("menuDishes")
    public List<MenuDish> menuDishes;
    @SerializedName("promotions")
    public List<Promotion> promotions;

//    public  BrandViewModel Brand { get; set; }
//
//    public  StoreCategoryViewModel StoreCategory { get; set; }


    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<MenuDish> getMenuDishes() {
        return menuDishes;
    }

    public void setMenuDishes(List<MenuDish> menuDishes) {
        this.menuDishes = menuDishes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getNumberOfRating() {
        return numberOfRating;
    }

    public void setNumberOfRating(Long numberOfRating) {
        this.numberOfRating = numberOfRating;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }
}