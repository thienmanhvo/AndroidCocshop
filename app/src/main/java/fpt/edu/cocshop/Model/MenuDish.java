package fpt.edu.cocshop.Model;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MenuDish implements Serializable, Parent<Product> {
    @SerializedName("name")
    private String name;
    @SerializedName("products")
    private List<Product> products;
    @SerializedName("id")
    private String id;

    public MenuDish() {
    }

    public MenuDish(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuDish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Product> getChildList() {
        return products;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
