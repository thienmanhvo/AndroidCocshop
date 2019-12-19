package fpt.edu.cocshop.Retrofit;

public class ConfigApi {

    public static final String BASE_URL = "https://cocshopwebapi20190925023900.azurewebsites.net/api/";

    public interface Api {
        String LOGIN = "Auth/Login";
        String REGISTER = "Auth/Register";
        String PRODUCT = "Products";
        String STORE = "Stores";
        String GET_STORE_DETAIL = "Stores/GetStoreInfor/{id}";
        String GET_TOP_STORE = "Stores/GetTopStore";
        String BRAND = "Brands";
        String GET_NEAREST_STORE = "Stores/GetNearest";
        String GET_STORE_BY_ID = "Stores/{id}";
        String GET_PRODUCT_ID = "Products/{id}";
        String GET_PROFILE = "Auth/Profile";
        String PRODUCT2="Products";
        String SEARCH = "Products";
        String LOCATION="Locations";
        String ORDER = "Order";
    }
}
